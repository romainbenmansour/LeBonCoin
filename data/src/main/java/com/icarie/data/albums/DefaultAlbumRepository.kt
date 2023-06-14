package com.icarie.data.albums

import com.icarie.data.di.LocalDataSource
import com.icarie.data.di.RemoteDataSource
import com.icarie.domain.album.AlbumRepository
import javax.inject.Inject

class DefaultAlbumRepository @Inject constructor(
    @LocalDataSource localAlbumDataSource: AlbumDataSource,
    @RemoteDataSource remoteAlbumDataSource: AlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums() {
        TODO("Not yet implemented")
    }
}