package com.icarie.domain.network

import com.icarie.domain.common.Controller
import kotlinx.coroutines.flow.Flow

interface NetworkController : Controller {

    val networkStateFlow: Flow<NetworkState>

    fun getNetworkState(): NetworkState
    fun hasNetworkAccess(): Boolean
    fun isWifi(): Boolean
    fun isMobile(): Boolean
}
