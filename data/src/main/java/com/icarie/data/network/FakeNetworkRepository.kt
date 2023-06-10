package com.icarie.data.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.annotation.VisibleForTesting
import com.icarie.data.di.RepositoryCoroutineContext
import com.icarie.domain.network.NetworkRepository
import com.icarie.domain.network.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FakeNetworkRepository @Inject constructor(
    @RepositoryCoroutineContext coroutineContext: CoroutineContext,
) : NetworkRepository {

    private val coroutineScope: CoroutineScope = CoroutineScope(coroutineContext)

    override val networkStateFlow: MutableStateFlow<NetworkState> =
        MutableStateFlow(NetworkState.UNKNOWN)

    init {
        coroutineScope.launch {
            while (true) {
                dispatchNetworkUpdate(NetworkState.CONNECTED)
                delay(3000)
                dispatchNetworkUpdate(NetworkState.DISCONNECTED)
                delay(3000)
            }
        }
    }

    override fun start() {
    }

    override fun getNetworkState(): NetworkState = networkStateFlow.value

    override fun hasNetworkAccess(): Boolean = getNetworkState() == NetworkState.CONNECTED

    override fun isWifi(): Boolean = false

    override fun isMobile(): Boolean = false

    @VisibleForTesting
    fun dispatchNetworkUpdate(networkState: NetworkState) {
        coroutineScope.launch {
            Timber.i("dispatching network update: $networkState")
            networkStateFlow.emit(networkState)
        }
    }

    @VisibleForTesting
    inner class ConnectivityCallback : ConnectivityManager.NetworkCallback() {

        override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
            dispatchNetworkUpdate(NetworkState.CONNECTED)
        }

        override fun onLost(network: Network) {
            dispatchNetworkUpdate(NetworkState.DISCONNECTED)
        }
    }
}
