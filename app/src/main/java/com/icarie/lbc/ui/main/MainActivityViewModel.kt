package com.icarie.lbc.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCase
import com.icarie.domain.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    getNetworkStateUpdatesAsFlowUseCase: GetNetworkStateUpdatesAsFlowUseCase,
) : ViewModel() {

    val networkUpdates: StateFlow<NetworkState> = getNetworkStateUpdatesAsFlowUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = NetworkState.UNKNOWN
        )
}
