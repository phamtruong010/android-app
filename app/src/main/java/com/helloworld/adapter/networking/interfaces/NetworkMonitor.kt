package com.helloworld.adapter.networking.interfaces

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val networkState: Flow<NetworkState>

    data class NetworkState(
        val isOnline: Boolean,
        val shouldRefresh: Boolean
    )
}