package com.veko.data.repository

import com.veko.data.api.WeatherApi
import com.veko.data.model.getWeather.toEntityModel
import com.veko.data.storage.dao.WeatherDao
import com.veko.data.storage.entity.CityEntity
import com.veko.data.storage.entity.toDomainModel
import com.veko.data.utils.doOnSuccess
import com.veko.data.utils.safeRequest
import com.veko.domain.model.Weather
import com.veko.domain.useCase.WeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao,
    private val coroutineScope: CoroutineScope
) : WeatherUseCase {

    companion object {
        private const val DEFAULT_CITY = "Москва"
        private const val DEFAULT_EXCLUDE = "current"
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
                            lat = response.lat,
                            lon = response.lon,
                            exclude = exclude
                        )
                    }
                }
        }
    }

    private suspend fun getWeather(city: String, lat: Double, lon: Double, exclude: String) {
        safeRequest {
            api.getWeather(lat, lon, exclude)
                .doOnSuccess { response ->
                    coroutineScope.launch {
                        weatherDao.insertOrUpdate(response.toEntityModel(city))
                    }
                }
        }
    }

}