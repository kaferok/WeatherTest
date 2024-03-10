package com.veko.weatherexample.activity

import androidx.lifecycle.viewModelScope
import com.veko.common.connection.ConnectionManager
import com.veko.common.connection.ConnectionState
import com.veko.weatherexample.utils.BaseViewModel
import com.veko.weatherexample.utils.ViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WeatherActivityViewModel(
    private val connectionManager: ConnectionManager
) : BaseViewModel<ViewState, WeatherActivityViewAction>(
    initState = object : ViewState {}
) {

    init {
        observeConnection()
    }

    private fun observeConnection() {
        viewModelScope.launch {
            connectionManager.connectionState.collectLatest { state ->
                when(state){
                    ConnectionState.AVAILABLE -> sendAction(WeatherActivityViewAction.AvailableConnection)
                    ConnectionState.LOST -> sendAction(WeatherActivityViewAction.LostConnection)
                }
            }
        }
    }
}