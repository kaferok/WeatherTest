package com.veko.weatherexample.fragment.addCity

import com.veko.weatherexample.utils.ViewAction

sealed class AddCityViewAction : ViewAction {

    object Added : AddCityViewAction()

    class Error(val message: String) : AddCityViewAction()
}