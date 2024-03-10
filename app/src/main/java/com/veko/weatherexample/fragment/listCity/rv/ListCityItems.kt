package com.veko.weatherexample.fragment.listCity.rv

sealed class ListCityItems {

    data class Weather(
        val icon: String,
        val city: String,
        val temperature: String,
        val feelsLike: String,
        val pressure: String,
        val humidity: String,
        val visibility: String,
        val windSpeed: String,
        val isOpened: Boolean
    ) : ListCityItems()

    object AddButton : ListCityItems()
}