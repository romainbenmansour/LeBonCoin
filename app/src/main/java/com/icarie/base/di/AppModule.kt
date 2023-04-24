package com.icarie.base.di

import com.icarie.base.app.ControllerManager
import com.icarie.base.app.ControllerManagerImpl
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
