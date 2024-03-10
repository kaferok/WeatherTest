package com.veko.domain.model

data class DailyWeather(
    val date: Long,
    val temperature: Temperature,
    val feelsLike: Temperature,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val weatherDescription: WeatherDescription?
)