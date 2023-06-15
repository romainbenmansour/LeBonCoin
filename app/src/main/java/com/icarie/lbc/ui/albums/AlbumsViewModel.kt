package com.icarie.lbc.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.icarie.domain.album.GetAlbumsAsFlowUseCase
import com.icarie.domain.common.fold
import com.icarie.lbc.ui.UIState
import com.icarie.lbc.ui.toGlobalUIError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    getAlbumsAsFlowUseCase: GetAlbumsAsFlowUseCase,
) : ViewModel() {

    val uiState = getAlbumsAsFlowUseCase()
        .map { status ->
            status.fold(
                onSuccess = {
                    UIState.Success(
                        it.cachedIn(viewModelScope)
                            .also { flow -> Timber.e("normal $it vs cachedIn viewmodel $flow") })
                }, onFailure = {
                    UIState.Failure(it.toGlobalUIError())
                }
            )
        }
        .onStart { emit(UIState.Loading()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UIState.None()
        )
}
