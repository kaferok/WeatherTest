package com.veko.common.di

import com.veko.common.connection.ConnectionManager
import com.veko.common.connection.ConnectionManagerImpl
import com.veko.common.handler.exception.NetworkExceptionHandler
import com.veko.common.handler.exception.NetworkExceptionHandlerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    single<ConnectionManager> { ConnectionManagerImpl(androidContext()) }
    single<NetworkExceptionHandler> { NetworkExceptionHandlerImpl() }
}