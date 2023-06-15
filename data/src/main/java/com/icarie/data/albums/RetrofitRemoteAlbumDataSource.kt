package com.icarie.data.albums

import com.icarie.domain.common.Status
import com.icarie.domain.errors.AppError
import com.icarie.domain.models.Album
import javax.inject.Inject

interface RemoteAlbumDataSource {
    suspend fun fetchAlbums(): Status<List<Album>>
}

class RetrofitRemoteAlbumDataSource @Inject constructor(
    private val albumRetrofitService: AlbumRetrofitService
) : RemoteAlbumDataSource {

    override suspend fun fetchAlbums(): Status<List<Album>> {
        albumRetrofitService.getAlbums().apply {
            return when {
                isSuccessful -> Status.Success(body() ?: emptyList())
                else -> Status.Error(AppError.Offline)
            }
        }
    }
}
