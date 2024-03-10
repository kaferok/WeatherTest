package com.veko.domain.repository

import com.veko.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun observeCityWeather(): Flow<List<Weather>>

    suspend fun getCoords(city: String, exclude: String)
}