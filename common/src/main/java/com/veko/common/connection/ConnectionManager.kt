package com.veko.common.connection

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ConnectionManager {

    val connectionState: StateFlow<ConnectionState>
}