package com.icarie.data.di

import com.icarie.data.albums.cache.LocalAlbumDataSource
import com.icarie.data.albums.remote.RemoteAlbumDataSource
import com.icarie.data.albums.remote.RetrofitRemoteAlbumDataSource
import com.icarie.data.albums.cache.RoomLocalAlbumDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLocalAlbumDataSource(impl: RoomLocalAlbumDataSource): LocalAlbumDataSource

    @Binds
    fun bindRemoteAlbumDataSource(impl: RetrofitRemoteAlbumDataSource): RemoteAlbumDataSource
}
