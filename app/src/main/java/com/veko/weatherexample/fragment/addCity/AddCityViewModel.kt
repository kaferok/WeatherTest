package com.veko.weatherexample.fragment.addCity

import androidx.lifecycle.viewModelScope
import com.veko.common.handler.exception.NetworkExceptionHandler
import com.veko.common.handler.exception.NetworkExceptionState
import com.veko.domain.useCase.WeatherUseCase
import com.veko.weatherexample.R
import com.veko.weatherexample.utils.BaseViewModel
import com.veko.weatherexample.utils.ResourceProvider
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AddCityViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val exceptionHandler: NetworkExceptionHandler,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<AddCityViewState, AddCityViewAction>(
    initState = AddCityViewState()
) {

    init {
        observeException()
    }

    fun onCityChanged(text: String) {
        reduceState { oldState -> oldState.copy(city = text) }
    }

    fun onAddClicked() {
        viewModelScope.launch {
            val city = viewStateFlow.value.city
            val isCityExist = weatherUseCase.checkCityExist(city)
            if (isCityExist.not()) {
                weatherUseCase.getCoords(city = city)
                observeWeatherChanges()
            } else {
                sendAction(AddCityViewAction.Error(resourceProvider.getString(R.string.city_exist)))
            }
        }
    }

    private fun observeWeatherChanges() {
        viewModelScope.launch {
            weatherUseCase.observeCityWeather()
                .map {
                    val currentListSize = viewStateFlow.value.weatherListSize
                    if (currentListSize == 0) {
                        reduceState { oldState -> oldState.copy(weatherListSize = it.size) }
                        false
                    } else {
                        currentListSize != it.size
                    }
                }
                .onEach { reduceState { oldState -> oldState.copy(loading = true) } }
                .collectLatest {
                    sendAction(AddCityViewAction.Added)
                }
        }
    }

    private fun observeException() {
        viewModelScope.launch {
            exceptionHandler
                .observeNetworkException()
                .onEach { reduceState { oldState -> oldState.copy(loading = false) } }
                .collectLatest { exception ->
                    val message = when (exception) {
                        NetworkExceptionState.COMMON -> resourceProvider.getString(R.string.common)
                        NetworkExceptionState.NOT_FOUND -> resourceProvider.getString(R.string.not_found)
                        NetworkExceptionState.TO_MANY_REQUESTS -> resourceProvider.getString(R.string.to_many_requests)
                    }
                    sendAction(AddCityViewAction.Error(message))
                }
        }
    }
}