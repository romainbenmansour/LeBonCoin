package com.icarie.base.ui.home

import com.icarie.domain.network.NetworkState
import javax.inject.Inject

interface HomeScreenUIDataTransformer {
    operator fun invoke(networkState: NetworkState) : HomeScreenUIData
}

class DefaultHomeScreenUIDataTransformer @Inject constructor(): HomeScreenUIDataTransformer {

    override fun invoke(networkState: NetworkState): HomeScreenUIData =
        HomeScreenUIData(
            connected = networkState == NetworkState.CONNECTED
        )
}
