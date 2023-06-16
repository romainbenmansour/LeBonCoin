package com.icarie.lbc.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.icarie.domain.albums.GetAlbumsUseCase
import com.icarie.domain.albums.GetPagedAlbumsUseCase
import com.icarie.domain.albums.Album
import com.icarie.lbc.ui.UIState
import com.icarie.lbc.ui.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    getAlbumsUseCase: GetAlbumsUseCase,
    getPagedAlbumsUseCase: GetPagedAlbumsUseCase,
) : ViewModel() {

    val albumFlow: Flow<PagingData<Album>> = getPagedAlbumsUseCase()
        .cachedIn(viewModelScope)

    val uiState = flow { emit(getAlbumsUseCase()) }
        .map { status -> status.toUIState() }
        .onStart { emit(UIState.Loading()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UIState.None()
        )
}
