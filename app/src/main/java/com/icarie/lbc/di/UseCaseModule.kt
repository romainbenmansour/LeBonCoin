package com.icarie.lbc.di

import com.icarie.domain.albums.GetAlbumsUseCaseImpl
import com.icarie.domain.albums.GetPagedAlbumsUseCaseImpl
import com.icarie.domain.albums.GetAlbumsUseCase
import com.icarie.domain.albums.GetPagedAlbumsUseCase
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCase
import com.icarie.domain.network.GetNetworkStateUpdatesAsFlowUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetNetworkStateUpdatesAsFlowUseCase(impl: GetNetworkStateUpdatesAsFlowUseCaseImpl): GetNetworkStateUpdatesAsFlowUseCase

    @Binds
    fun bindGetAlbumsUseCase(impl: GetAlbumsUseCaseImpl): GetAlbumsUseCase

    @Binds
    fun bindGetPagedAlbumsUseCase(impl: GetPagedAlbumsUseCaseImpl): GetPagedAlbumsUseCase
}
