package com.icarie.data.di

import com.icarie.data.network.FakeNetworkRepository
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
    fun bindNetworkController(impl: FakeNetworkRepository): NetworkRepository
}
