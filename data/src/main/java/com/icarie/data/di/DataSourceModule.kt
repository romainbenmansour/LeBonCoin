package com.icarie.data.di

import com.icarie.data.albums.AlbumDataSource
import com.icarie.data.albums.LocalAlbumDataSource
import com.icarie.data.albums.RemoteAlbumDataSource
import com.icarie.data.network.FakeNetworkRepository
import com.icarie.domain.album.AlbumRepository
import com.icarie.domain.network.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    @LocalDataSource
    fun bindLocalAlbumDataSource(impl: LocalAlbumDataSource): AlbumDataSource

    @Binds
    @Singleton
    @RemoteDataSource
    fun bindRemoteAlbumDataSource(impl: RemoteAlbumDataSource): AlbumDataSource
}
