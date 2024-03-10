package com.veko.weatherexample.fragment.currentWeather

import androidx.lifecycle.viewModelScope
import com.veko.domain.model.Weather
import com.veko.domain.useCase.WeatherUseCase
import com.veko.weatherexample.R
import com.veko.weatherexample.fragment.currentWeather.rv.WeatherItems
import com.veko.weatherexample.utils.BaseViewModel
import com.veko.weatherexample.utils.ResourceProvider
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<WeatherViewState, WeatherViewAction>(
    initState = WeatherViewState()
) {

    init {
        observeWeather()
    }

    fun onAddClicked() {
        viewModelScope.launch {
            sendAction(WeatherViewAction.AddNew)
        }
    }

    fun onArrowClicked(item: WeatherItems.Weather) {
        val items = viewStateFlow.value.weather
        val updatedItems = items.map {
            when (it) {
                item -> (it as WeatherItems.Weather).copy(isOpened = item.isOpened.not())
                else -> it
            }
        }
        reduceState { oldState -> oldState.copy(weather = updatedItems) }
    }

    private fun observeWeather() {
        viewModelScope.launch {
            weatherUseCase.observeCityWeather()
                .map { buildItems(it) }
                .collectLatest {
                    reduceState { oldState -> oldState.copy(weather = it) }
                }
        }
    }

    private fun buildItems(weather: List<Weather>): List<WeatherItems> {
        return weather.map { weatherItem ->
            WeatherItems.Weather(
                icon = weatherItem.currentWeather.weatherDescription?.icon.orEmpty(),
                city = weatherItem.city,
                temperature = resourceProvider.getString(
                    R.string.c,
                    weatherItem.currentWeather.temperature.toString()
                ),
                feelsLike = resourceProvider.getString(
                    R.string.feels_like,
                    weatherItem.currentWeather.feelsLike.toString()
                ),
                pressure = resourceProvider.getString(
                    R.string.pressure,
                    weatherItem.currentWeather.pressure.toString()
                ),
                humidity = resourceProvider.getString(
                    R.string.humidity,
                    weatherItem.currentWeather.humidity.toString()
                ),
                visibility = resourceProvider.getString(
                    R.string.visibility,
                    weatherItem.currentWeather.visibility.toString()
                ),
                windSpeed = resourceProvider.getString(
                    R.string.wind_speed,
                    weatherItem.currentWeather.windSpeed.toString()
                ),
                main = weatherItem.currentWeather.weatherDescription?.main.orEmpty(),
            )
        } + listOf(WeatherItems.AddButton)
    }
}