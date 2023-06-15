package com.icarie.data.di

import com.icarie.data.albums.LocalAlbumDataSource
import com.icarie.data.albums.RemoteAlbumDataSource
import com.icarie.data.albums.RetrofitRemoteAlbumDataSource
import com.icarie.data.albums.RoomLocalAlbumDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLocalAlbumDataSource(impl: RoomLocalAlbumDataSource): LocalAlbumDataSource

    @Binds
    fun bindRemoteAlbumDataSource(impl: RetrofitRemoteAlbumDataSource): RemoteAlbumDataSource
}
