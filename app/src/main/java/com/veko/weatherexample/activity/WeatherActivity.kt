package com.veko.weatherexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.veko.weatherexample.R

class WeatherActivity : AppCompatActivity(), ActivityBinder {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateTo(direction: NavDirections) {
        findNavController(R.id.mainContainer).navigate(direction)
    }

    override fun navigateUp() {
        findNavController(R.id.mainContainer).navigateUp()
    }
}