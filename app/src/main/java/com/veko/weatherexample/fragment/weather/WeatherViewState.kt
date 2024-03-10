package com.veko.weatherexample.fragment.weather

import com.veko.weatherexample.fragment.weather.rv.WeatherItems
import com.veko.weatherexample.utils.ViewState

data class WeatherViewState(
    val weather: List<WeatherItems> = emptyList(),
) : ViewState