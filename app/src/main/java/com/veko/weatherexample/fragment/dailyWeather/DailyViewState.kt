package com.veko.weatherexample.fragment.dailyWeather

import com.veko.weatherexample.fragment.dailyWeather.rv.DailyItem
import com.veko.weatherexample.utils.ViewState


data class DailyViewState(
    val weather: List<DailyItem> = emptyList()
) : ViewState