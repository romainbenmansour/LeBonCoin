package com.icarie.base.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.base.ui.home.MainScreenUIDataTransformer
import com.icarie.base.ui.compose.states.UIState
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getNetworkStateUpdatesAsFlowUseCase: GetNetworkStateUpdatesAsFlowUseCase,
    private val mainScreenUIDataTransformer: MainScreenUIDataTransformer
) : ViewModel() {

    val uiStateData = getNetworkStateUpdatesAsFlowUseCase()
        .map { mainScreenUIDataTransformer(it) }
        .map { UIState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UIState.None()
        )
}