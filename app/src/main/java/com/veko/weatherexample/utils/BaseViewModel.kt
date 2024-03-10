package com.veko.weatherexample.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State : ViewState, Action : ViewAction>(
    initState: State
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(initState)
    val viewStateFlow = _viewStateFlow.asStateFlow()

    private val _viewActionFlow = Channel<Action>()
    val actionFlow = _viewActionFlow.receiveAsFlow()

    protected fun reduceState(reducer: (State) -> State) {
        val oldState = _viewStateFlow.value
        _viewStateFlow.tryEmit(reducer(oldState))
    }

    protected suspend fun sendAction(action: Action) {
        _viewActionFlow.send(action)
    }
}