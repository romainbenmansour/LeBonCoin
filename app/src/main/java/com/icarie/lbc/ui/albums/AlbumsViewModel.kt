package com.icarie.lbc.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.icarie.domain.album.GetAlbumsAsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    getAlbumsAsFlowUseCase: GetAlbumsAsFlowUseCase,
    private val albumTransformer: AlbumTransformer,
) : ViewModel() {

    val uiState = getAlbumsAsFlowUseCase().cachedIn(viewModelScope)
}
