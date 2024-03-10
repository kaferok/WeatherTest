package com.veko.weatherexample.fragment.dailyWeather

import androidx.lifecycle.viewModelScope
import com.veko.domain.model.Weather
import com.veko.domain.useCase.WeatherUseCase
import com.veko.weatherexample.R
import com.veko.weatherexample.fragment.dailyWeather.rv.DailyItem
import com.veko.weatherexample.utils.BaseViewModel
import com.veko.weatherexample.utils.ResourceProvider
import com.veko.weatherexample.utils.ViewAction
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DailyViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<DailyViewState, ViewAction>(
    initState = DailyViewState()
) {

    init {
        observeWeather()
    }

    private fun observeWeather() {
        viewModelScope.launch {
            weatherUseCase.observeCityWeather()
                .map {
                    buildItems(it)
                }
                .collectLatest {
                    reduceState { oldState -> oldState.copy(weather = it) }
                }
        }
    }

    private fun buildItems(weather: List<Weather>): List<DailyItem> =
        weather
            .flatMap { it.dailyWeather }
            .map {
                DailyItem(
                    icon = it.weatherDescription?.icon.orEmpty(),
                    temperature = resourceProvider.getString(
                        R.string.c,
                        it.temperature.day.toString()
                    ),
                    main = it.weatherDescription?.main.orEmpty(),
                    date = it.date.toString()
                )
            }
}