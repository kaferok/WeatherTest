package com.veko.weatherexample

import android.app.Application
import com.veko.data.di.dataModule
import com.veko.domain.di.domainModule
import com.veko.weatherexample.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(dataModule, domainModule, presenterModule)
        }
    }
}