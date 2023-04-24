package com.icarie.base.di

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
}
