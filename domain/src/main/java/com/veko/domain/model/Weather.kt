package com.veko.domain.model

data class Weather(
    val city: String,
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val visibility: Int,
    val windSpeed: Double,
    val weatherDescription: WeatherDescription?
)