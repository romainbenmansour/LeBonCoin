package com.icarie.data.di

import com.icarie.data.albums.DefaultAlbumRepository
import com.icarie.data.network.NetworkRepositoryImpl
import com.icarie.domain.album.AlbumRepository
import com.icarie.domain.network.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindNetworkRepository(impl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    @Singleton
    fun bindAlbumRepository(impl: DefaultAlbumRepository): AlbumRepository
}
