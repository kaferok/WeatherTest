package com.veko.domain.model

data class Weather(
    val city: String,
    val timeZoneOffset: Long,
    val currentWeather: CurrentWeather,
    val dailyWeather: List<DailyWeather>
)