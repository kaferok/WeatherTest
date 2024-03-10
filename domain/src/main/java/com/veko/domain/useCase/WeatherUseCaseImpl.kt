package com.veko.domain.useCase

import com.veko.domain.model.Weather
import com.veko.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherUseCaseImpl(
    private val repository: WeatherRepository
) : WeatherUseCase {
    override fun observeCityWeather(): Flow<List<Weather>> = repository.observeCityWeather()

    override suspend fun getCoords(city: String, exclude: String) =
        repository.getCoords(city, exclude)
}