package com.veko.weatherexample.fragment.currentWeather

import com.veko.weatherexample.fragment.currentWeather.rv.WeatherItems
import com.veko.weatherexample.utils.ViewState

data class WeatherViewState(
    val weather: List<WeatherItems> = emptyList(),
    val loading: Boolean = false
) : ViewState