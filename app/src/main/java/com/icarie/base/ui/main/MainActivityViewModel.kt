package com.icarie.base.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.base.navigation.Screen
import com.icarie.base.navigation.common.NavigationManager
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCase
import com.icarie.domain.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    navigationManager: NavigationManager,
    getNetworkStateUpdatesAsFlowUseCase: GetNetworkStateUpdatesAsFlowUseCase,
) : ViewModel() {

    val isBottomContentEnabled =
        navigationManager.screenUpdates
            .map { it.hasBottomSheet }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = false
            )

    val networkUpdates: StateFlow<NetworkState> = getNetworkStateUpdatesAsFlowUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = NetworkState.UNKNOWN
        )
}

private val Screen.hasBottomSheet: Boolean
    get() = listOf(Screen.Home).contains(this)
