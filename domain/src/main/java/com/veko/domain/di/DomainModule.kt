package com.veko.domain.di

import com.veko.domain.useCase.WeatherUseCase
import com.veko.domain.useCase.WeatherUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<WeatherUseCase> { WeatherUseCaseImpl(get())}
}