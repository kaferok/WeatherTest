package com.veko.domain.useCase

import com.veko.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {

    fun observeCityWeather(): Flow<List<Weather>>

    suspend fun getCoords(city: String, exclude: String)

}