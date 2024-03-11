package com.veko.data.repository

import com.veko.data.api.WeatherApi
import com.veko.common.connection.ConnectionManager
import com.veko.common.connection.ConnectionState
import com.veko.common.handler.exception.NetworkExceptionHandler
import com.veko.data.model.getWeather.toEntityModel
import com.veko.data.storage.dao.WeatherDao
import com.veko.data.storage.entity.WeatherEntity
import com.veko.data.storage.entity.toDomainModel
import com.veko.domain.model.Weather
import com.veko.domain.repository.WeatherRepository
import com.veko.common.utils.withLatestFrom
import com.veko.data.utils.Result
import com.veko.data.utils.bodyOrFailure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao,
    private val coroutineScope: CoroutineScope,
    private val connectionManager: ConnectionManager,
    private val exceptionHandler: NetworkExceptionHandler
) : WeatherRepository {

    companion object {
        private const val DEFAULT_CITY = "Москва"
        private const val DEFAULT_EXCLUDE = "minutely,hourly,alerts"
    }

    init {
        addFirstWeather()
        updateExist()
    }

    override fun observeCityWeather(): Flow<List<Weather>> =
        weatherDao.getWeatherByCoords()
            .filterNot { it.isEmpty() }
            .map { it.map(WeatherEntity::toDomainModel) }

    override suspend fun getCoords(city: String, exclude: String) {
        val result = api.getCoordsByCity(city = city).bodyOrFailure()
        when (result) {
            is Result.Success -> {
                if (result.value.isEmpty()) {
                    exceptionHandler.update(404)
                } else {
                    coroutineScope.launch {
                        getWeather(
                            city = result.value.firstOrNull()?.locale?.ru.orEmpty(),
                            lat = result.value.firstOrNull()?.lat ?: 0.0,
                            lon = result.value.firstOrNull()?.lon ?: 0.0,
                            exclude = exclude
                        )
                    }
                }
            }

            is Result.Failure -> {
                exceptionHandler.update(result.errorCode)
            }
        }
    }

    override suspend fun checkCityExist(city: String): Boolean = weatherDao.isCityExist(city)

    private suspend fun getWeather(
        city: String,
        lat: Double,
        lon: Double,
        exclude: String = DEFAULT_EXCLUDE
    ) {
        coroutineScope.launch {
            val result = api.getWeather(lat, lon, exclude).bodyOrFailure()
            when (result) {
                is Result.Success -> {
                    weatherDao.insertOrUpdate(result.value.toEntityModel(city))
                }

                is Result.Failure -> {
                    exceptionHandler.update(result.errorCode)
                }
            }
        }
    }

    private fun updateExist() {
        coroutineScope.launch {
            weatherDao
                .getWeatherByCoords()
                .withLatestFrom(connectionManager.connectionState) { entities, connection -> entities to connection }
                .filter { (entities, connection) -> connection == ConnectionState.AVAILABLE && entities.isNotEmpty() }
                .map { (entities, _) -> entities }
                .cancellable()
                .collectLatest { entities ->
                    entities.forEach { city ->
                        getWeather(city = city.latest.city, lat = city.lat, lon = city.lon)
                    }
                    this.coroutineContext.job.cancel()
                }
        }
    }

    private fun addFirstWeather() {
        coroutineScope.launch {
            val isWeatherEmpty = weatherDao.isEmpty()
            if (isWeatherEmpty) {
                getCoords(city = DEFAULT_CITY, exclude = DEFAULT_EXCLUDE)
            }
        }
    }
}