package com.veko.weatherexample.fragment.currentWeather

import com.veko.weatherexample.utils.ViewAction

sealed class WeatherViewAction : ViewAction {

    object AddNew : WeatherViewAction()
}