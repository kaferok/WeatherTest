package com.veko.weatherexample.fragment.weather.rv

import com.veko.weatherexample.utils.EMPTY_STRING

sealed class WeatherItems {

    data class Weather(
        val icon: String = EMPTY_STRING,
        val city: String = EMPTY_STRING,
        val temperature: String = EMPTY_STRING,
        val feelsLike: String = EMPTY_STRING,
        val pressure: String = EMPTY_STRING,
        val humidity: String = EMPTY_STRING,
        val visibility: String = EMPTY_STRING,
        val windSpeed: String = EMPTY_STRING,
        val main: String = EMPTY_STRING,
        val isOpened: Boolean = false
    ) : WeatherItems()

    object AddButton : WeatherItems()
}