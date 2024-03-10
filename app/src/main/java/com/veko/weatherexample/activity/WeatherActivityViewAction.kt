package com.veko.weatherexample.activity

import com.veko.weatherexample.utils.ViewAction

sealed class WeatherActivityViewAction : ViewAction {

    object AvailableConnection: WeatherActivityViewAction()

    object LostConnection: WeatherActivityViewAction()
}