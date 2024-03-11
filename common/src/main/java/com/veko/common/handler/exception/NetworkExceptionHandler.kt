package com.veko.common.handler.exception

import android.net.http.HttpException
import kotlinx.coroutines.flow.Flow

interface NetworkExceptionHandler {

    fun update(code: Int)

    fun observeNetworkException(): Flow<NetworkExceptionState>
}