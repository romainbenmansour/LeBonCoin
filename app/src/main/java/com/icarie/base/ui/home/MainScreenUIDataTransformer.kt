package com.icarie.base.ui.home

import com.icarie.domain.network.NetworkState
import javax.inject.Inject

interface MainScreenUIDataTransformer {
    operator fun invoke(networkState: NetworkState) : MainScreenUIData
}

class DefaultMainScreenUIDataTransformer @Inject constructor(): MainScreenUIDataTransformer {

    override fun invoke(networkState: NetworkState): MainScreenUIData =
        MainScreenUIData(
            connected = networkState == NetworkState.CONNECTED
        )
}
