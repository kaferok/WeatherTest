package com.veko.weatherexample.di

import com.veko.weatherexample.activity.WeatherActivityViewModel
import com.veko.weatherexample.fragment.addCity.AddCityViewModel
import com.veko.weatherexample.fragment.weather.WeatherViewModel
import com.veko.weatherexample.utils.ResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {

    single { ResourceProvider(androidContext()) }

    viewModel { WeatherViewModel(get(), get()) }
    viewModel { AddCityViewModel(get()) }
    viewModel { WeatherActivityViewModel(get()) }
}