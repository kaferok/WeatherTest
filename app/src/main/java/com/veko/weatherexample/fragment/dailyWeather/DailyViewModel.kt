package com.veko.weatherexample.fragment.dailyWeather

import androidx.lifecycle.viewModelScope
import com.veko.domain.model.Weather
import com.veko.domain.useCase.WeatherUseCase
import com.veko.domain.utils.DateUtils
import com.veko.weatherexample.R
import com.veko.weatherexample.fragment.dailyWeather.rv.DailyItem
import com.veko.weatherexample.utils.BaseViewModel
import com.veko.weatherexample.utils.ResourceProvider
import com.veko.weatherexample.utils.ViewAction
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class DailyViewModel(
    city: String,
    private val weatherUseCase: WeatherUseCase,
    private val resourceProvider: ResourceProvider,
    private val dateUtils: DateUtils
) : BaseViewModel<DailyViewState, ViewAction>(
    initState = DailyViewState()
) {

    init {
        observeWeather(city)
    }

    private fun observeWeather(city: String) {
        viewModelScope.launch {
            weatherUseCase.observeCityWeather()
                .map { buildItems(it.first { it.city == city }) }
                .collectLatest {
                    reduceState { oldState -> oldState.copy(weather = it) }
                }
        }
    }

    private fun buildItems(weather: Weather): List<DailyItem> =
        weather.dailyWeather
            .map {
                DailyItem(
                    icon = it.weatherDescription?.icon.orEmpty(),
                    temperature = resourceProvider.getString(
                        R.string.c,
                        it.temperature.day.roundToInt().toString()
                    ),
                    main = it.weatherDescription?.main.orEmpty(),
                    date = dateUtils.formatDailyWeather(it.date, weather.timeZoneOffset)
                )
            }
}