package com.icarie.data.di

import com.icarie.data.common.StringRetrieverImpl
import com.icarie.domain.utils.StringRetriever
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    fun bindStringRetriever(impl: StringRetrieverImpl): StringRetriever
}
