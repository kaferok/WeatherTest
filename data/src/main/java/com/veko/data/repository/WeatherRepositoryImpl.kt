package com.veko.data.repository

import com.veko.data.api.WeatherApi
import com.veko.common.connection.ConnectionManager
import com.veko.common.connection.ConnectionState
import com.veko.data.model.getWeather.toEntityModel
import com.veko.data.storage.dao.WeatherDao
import com.veko.data.storage.entity.CityEntity
import com.veko.data.storage.entity.toDomainModel
import com.veko.data.utils.doOnSuccess
import com.veko.data.utils.safeRequest
import com.veko.domain.model.Weather
import com.veko.domain.repository.WeatherRepository
import com.veko.common.utils.withLatestFrom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao,
    private val coroutineScope: CoroutineScope,
    private val connectionManager: ConnectionManager
) : WeatherRepository {

    companion object {
        private const val DEFAULT_CITY = "Moscow"
        private const val DEFAULT_EXCLUDE = "current"
    }

    init {
        updateExist()
    }

    override fun observeCityWeather(): Flow<List<Weather>> =
        weatherDao.getWeatherByCoords()
            .onEmpty {
                getCoords(city = DEFAULT_CITY, exclude = DEFAULT_EXCLUDE)
            }
            .map { it.map(CityEntity::toDomainModel) }

    override suspend fun getCoords(city: String, exclude: String) {
        safeRequest {
            api.getCoordsByCity(city = city)
                .doOnSuccess { response ->
                    coroutineScope.launch {
                        getWeather(
                            city = city,
                            lat = response.firstOrNull()?.lat ?: 0.0,
                            lon = response.firstOrNull()?.lon ?: 0.0,
                            exclude = exclude
                        )
                    }
                }
        }
    }

    private suspend fun getWeather(
        city: String,
        lat: Double,
        lon: Double,
        exclude: String = "current"
    ) {
        safeRequest {
            api.getWeather(lat, lon, exclude)
                .doOnSuccess { response ->
                    coroutineScope.launch {
                        weatherDao.insertOrUpdate(response.toEntityModel(city))
                    }
                }
        }
    }

    private fun updateExist() {
        coroutineScope.launch {
            weatherDao
                .getWeatherByCoords()
                .withLatestFrom(connectionManager.connectionState) { entities, connection -> entities to connection }
                .onEach { (_, connection) ->
                    when (connection) {
                        ConnectionState.AVAILABLE -> Unit
                        ConnectionState.LOST -> cancel()
                    }
                }
                .map { (entities, _) -> entities }
                .onEmpty { cancel() }
                .collectLatest { entities ->
                    entities.forEach {city ->
                        getWeather(city = city.latestWeather.city, lat = city.lat, lon = city.lon)
                    }
                    cancel()
                }
        }
    }
}