package com.veko.data.di

import com.veko.data.AppDispatchers
import com.veko.data.api.WeatherApi
import com.veko.data.repository.WeatherRepositoryImpl
import com.veko.data.retorift.ApiKeyInterceptor
import com.veko.data.retorift.RetrofitBuilder
import com.veko.data.storage.WeatherDatabase
import com.veko.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single { RetrofitBuilder.getLoggingInterceptor() }
    single { ApiKeyInterceptor() }
    single { RetrofitBuilder.buildHttpClient(get(), get()) }

    single<WeatherApi> {
        RetrofitBuilder.build(get()).create(WeatherApi::class.java)
    }

    single { WeatherDatabase.getInstance(androidContext()) }
    single { get<WeatherDatabase>().weatherDao() }

    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get(), get(), get()) }

    single { AppDispatchers }

    single {
        CoroutineScope(SupervisorJob() + get<AppDispatchers>().io)
    }
}