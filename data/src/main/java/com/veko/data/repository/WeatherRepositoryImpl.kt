package com.veko.data.repository

import com.veko.data.api.WeatherApi

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val weatherDao: Any
) {
}