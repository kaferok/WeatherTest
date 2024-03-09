package com.veko.data.di

import com.veko.data.api.WeatherApi
import com.veko.data.retorift.RetrofitBuilder
import com.veko.data.storage.WeatherDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single<WeatherApi> { RetrofitBuilder.build().create(WeatherApi::class.java) }
    single { WeatherDatabase.create(androidApplication()) }
}