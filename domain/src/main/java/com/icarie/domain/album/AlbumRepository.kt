package com.icarie.domain.album

import com.icarie.domain.common.Status
import com.icarie.domain.models.Album

interface AlbumRepository {
    suspend fun getAlbums(): Status<List<Album>>
}