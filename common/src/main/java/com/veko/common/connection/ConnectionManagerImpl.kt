package com.veko.common.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ConnectionManagerImpl(context: Context) : ConnectionManager {

    private val systemManager = context.getSystemService<ConnectivityManager>()

    private val _connectionState = MutableStateFlow(ConnectionState.LOST)

    override val connectionState: StateFlow<ConnectionState>
        get() {
            systemManager?.registerNetworkCallback(networkRequest, networkCallback)
            return _connectionState.asStateFlow()
        }

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _connectionState.tryEmit(ConnectionState.AVAILABLE)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _connectionState.tryEmit(ConnectionState.LOST)
        }
    }

}