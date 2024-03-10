package com.veko.weatherexample.activity

import androidx.navigation.NavDirections

interface ActivityBinder {
    fun navigateTo(direction: NavDirections)

    fun navigateUp()
}