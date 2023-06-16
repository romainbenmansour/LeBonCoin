package com.icarie.lbc.di

import com.icarie.domain.albums.DefaultGetAlbumsUseCase
import com.icarie.domain.albums.DefaultGetPagedAlbumsUseCase
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
    fun bindGetAlbumsUseCase(impl: DefaultGetAlbumsUseCase): GetAlbumsUseCase

    @Binds
    fun bindGetPagedAlbumsUseCase(impl: DefaultGetPagedAlbumsUseCase): GetPagedAlbumsUseCase
}
