package com.icarie.base.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNetworkStateUpdatesAsFlowUseCase: GetNetworkStateUpdatesAsFlowUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getNetworkStateUpdatesAsFlowUseCase.invoke().collect {
                Timber.e("state update : $it")
            }
        }
    }
}