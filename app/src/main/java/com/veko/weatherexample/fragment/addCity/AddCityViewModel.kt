package com.veko.weatherexample.fragment.addCity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veko.domain.useCase.WeatherUseCase
import com.veko.weatherexample.utils.BaseViewModel
import kotlinx.coroutines.launch

class AddCityViewModel(
    private val useCase: WeatherUseCase
) : BaseViewModel<AddCityViewState, AddCityViewAction>(
    initState = AddCityViewState()
) {

    fun onCityChanged(text: String) {
        reduceState { oldState -> oldState.copy(city = text) }
    }

    fun onAddClicked() {
        viewModelScope.launch {
            val city = viewStateFlow.value.city
            useCase.getCoords(city = city)
        }
    }
}