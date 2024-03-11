package com.veko.common.handler.exception

import android.net.http.HttpException
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class NetworkExceptionHandlerImpl : NetworkExceptionHandler {

    private val exceptionFlow = MutableSharedFlow<NetworkExceptionState>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun update(code: Int) {
        val state = when (code) {
            404 -> NetworkExceptionState.NOT_FOUND
            249 -> NetworkExceptionState.TO_MANY_REQUESTS
            else -> NetworkExceptionState.COMMON
        }
        exceptionFlow.tryEmit(state)
    }

    override fun observeNetworkException(): Flow<NetworkExceptionState> =
        exceptionFlow.asSharedFlow()
}