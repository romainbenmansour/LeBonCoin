package com.icarie.data.di

import com.icarie.data.network.FakeNetworkController
import com.icarie.data.network.NetworkControllerImpl
import com.icarie.domain.network.NetworkController
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
    fun bindNetworkController(impl: FakeNetworkController): NetworkController
}
