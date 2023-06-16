package com.icarie.data.albums.remote

import com.icarie.domain.common.Status
import com.icarie.domain.errors.AppError
import com.icarie.domain.albums.Album
import javax.inject.Inject

interface RemoteAlbumDataSource {
    suspend fun fetchAlbums(): Status<List<Album>>
}

class RetrofitRemoteAlbumDataSource @Inject constructor(
    private val albumRetrofitService: AlbumRetrofitService
) : RemoteAlbumDataSource {

    override suspend fun fetchAlbums(): Status<List<Album>> {
        try {
            albumRetrofitService.getAlbums().apply {
                return when {
                    isSuccessful -> Status.Success(body() ?: emptyList())
                    else -> Status.Error(AppError.Offline)
                }
            }
        } catch (e: Throwable) {
            return Status.Error(AppError.Offline)
        }
    }
}
