package com.icarie.domain.albums

import com.icarie.domain.common.Status
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums(): Status<Unit>

    fun getAlbumsAsFlow(): Flow<PagingData<Album>>
}