package com.icarie.base.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.base.network.MainScreenUIDataTransformer
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getNetworkStateUpdatesAsFlowUseCase: GetNetworkStateUpdatesAsFlowUseCase,
    private val mainScreenUIDataTransformer: MainScreenUIDataTransformer
) : ViewModel() {

    val state = getNetworkStateUpdatesAsFlowUseCase()
        .onEach { Timber.e("network state update: $it") }
        .map { mainScreenUIDataTransformer(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = DEFAULT_NETWORK_VALUE
        )

    fun toto() {
        Timber.e("toto")
    }

    private companion object {
        private const val DEFAULT_NETWORK_VALUE = false
    }
}