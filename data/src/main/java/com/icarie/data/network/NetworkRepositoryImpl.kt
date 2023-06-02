package com.icarie.data.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.annotation.VisibleForTesting
import com.icarie.data.di.RepositoryCoroutineContext
import com.icarie.domain.network.NetworkRepository
import com.icarie.domain.network.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NetworkRepositoryImpl @Inject constructor(
    @RepositoryCoroutineContext coroutineContext: CoroutineContext,
    private val connectivityManager: ConnectivityManager
) : NetworkRepository {

    private val coroutineScope: CoroutineScope = CoroutineScope(coroutineContext)

    @VisibleForTesting
    val networkCallback: ConnectivityCallback = ConnectivityCallback()

    override val networkStateFlow: MutableStateFlow<NetworkState> =
        MutableStateFlow(NetworkState.UNKNOWN)

    init {
        networkStateFlow.value = getNetworkState()
    }

    override fun start() {
        subscribeToNetworkUpdates()
    }

    override fun getNetworkState(): NetworkState =
        connectivityManager
            .getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.let { NetworkState.CONNECTED }
            ?: NetworkState.DISCONNECTED

    override fun hasNetworkAccess(): Boolean = getNetworkState() == NetworkState.CONNECTED

    override fun isWifi(): Boolean =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false

    override fun isMobile(): Boolean =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            } ?: false

    private fun subscribeToNetworkUpdates() {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    @VisibleForTesting
    fun dispatchNetworkUpdate(networkState: NetworkState) {
        if (networkStateFlow.value == networkState) {
            Timber.i("not dispatching network update for state: $networkState")
            return
        }
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
