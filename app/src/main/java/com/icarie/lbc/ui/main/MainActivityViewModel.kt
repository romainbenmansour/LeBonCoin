package com.icarie.lbc.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.domain.album.GetAlbumsAsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAlbumsAsFlowUseCase: GetAlbumsAsFlowUseCase
) : ViewModel() {

    fun fetch() {
        Timber.e("init")
        viewModelScope.launch {
            getAlbumsAsFlowUseCase.invoke().collect {
                Timber.e(it.toString())
            }
        }
    }
}
