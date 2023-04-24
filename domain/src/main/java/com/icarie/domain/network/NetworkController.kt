package com.icarie.domain.network

import com.deezer.zen.domain.common.Controller
import com.deezer.zen.domain.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface NetworkController : Controller {
    val networkStateFlow: Flow<NetworkState>
    fun getNetworkState(): NetworkState
    fun hasNetworkAccess(): Boolean
    fun isWifi(): Boolean
    fun isMobile(): Boolean
}
