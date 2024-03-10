package com.veko.weatherexample.fragment.addCity

import com.veko.weatherexample.utils.EMPTY_STRING
import com.veko.weatherexample.utils.ViewState

data class AddCityViewState(
    val city: String = EMPTY_STRING
) : ViewState