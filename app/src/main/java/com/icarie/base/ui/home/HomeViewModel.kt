package com.icarie.base.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.base.ui.compose.states.UIState
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getNetworkStateUpdatesAsFlowUseCase: GetNetworkStateUpdatesAsFlowUseCase,
    private val homeScreenUIDataTransformer: HomeScreenUIDataTransformer
) : ViewModel() {

    val uiStateData = getNetworkStateUpdatesAsFlowUseCase()
        .map { homeScreenUIDataTransformer(it) }
        .map { UIState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UIState.None()
        )
}