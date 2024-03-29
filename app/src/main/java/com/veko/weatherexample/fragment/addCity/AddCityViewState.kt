package com.veko.weatherexample.fragment.addCity

import com.veko.weatherexample.utils.EMPTY_STRING
import com.veko.weatherexample.utils.ViewState

data class AddCityViewState(
    val city: String = EMPTY_STRING,
    val weatherListSize: Int = 0,
    val loading: Boolean = false,
    val hasConnection: Boolean = true,
    val textError: String? = null
) : ViewState