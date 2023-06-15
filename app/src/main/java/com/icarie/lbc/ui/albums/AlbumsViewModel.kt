package com.icarie.lbc.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.domain.album.GetAlbumsAsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getAlbumsAsFlowUseCase: GetAlbumsAsFlowUseCase
) : ViewModel() {

    fun fetch() {
        viewModelScope.launch {
            getAlbumsAsFlowUseCase.invoke().collect {
                Timber.e(it.toString())
            }
        }
    }
}
