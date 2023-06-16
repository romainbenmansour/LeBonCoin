package com.icarie.data.di

import com.icarie.data.albums.AlbumRepositoryImpl
import com.icarie.data.network.NetworkRepositoryImpl
import com.icarie.domain.albums.AlbumRepository
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
    fun bindAlbumRepository(impl: AlbumRepositoryImpl): AlbumRepository
}
