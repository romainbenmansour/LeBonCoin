package com.icarie.domain.albums

import com.icarie.domain.common.Status
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums(pageSize: Int = PAGE_SIZE): Status<Unit>

    fun getAlbumsAsFlow(): Flow<PagingData<Album>>

    companion object {
        const val PAGE_SIZE = 10
    }
}