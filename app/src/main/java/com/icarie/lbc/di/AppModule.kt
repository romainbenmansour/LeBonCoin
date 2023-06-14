package com.icarie.lbc.di

import com.icarie.lbc.app.ControllerManager
import com.icarie.lbc.app.ControllerManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindControllerManager(impl: ControllerManagerImpl): ControllerManager
}
