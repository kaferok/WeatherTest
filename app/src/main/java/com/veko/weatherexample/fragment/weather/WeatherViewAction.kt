package com.veko.weatherexample.fragment.weather

import com.veko.weatherexample.utils.ViewAction

sealed class WeatherViewAction : ViewAction {

    object AddNew : WeatherViewAction()
}