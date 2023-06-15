package com.icarie.domain.album

import com.icarie.domain.common.Status
import com.icarie.domain.models.Album
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums(pageSize: Int = PAGE_SIZE): Status<List<Album>>

    fun getAlbumsAsFlow(): Flow<PagingData<Album>>

    private companion object {
        const val PAGE_SIZE = 10
    }
}