package com.veko.common.connection

import kotlinx.coroutines.flow.SharedFlow

interface ConnectionManager {

    val connectionState: SharedFlow<ConnectionState>
}