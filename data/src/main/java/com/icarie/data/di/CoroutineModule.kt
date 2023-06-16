package com.icarie.data.di

import com.icarie.domain.common.UseCaseCoroutineContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext

@Qualifier
annotation class RepositoryCoroutineContext

@Qualifier
annotation class DataSourceCoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @UseCaseCoroutineContext
    fun providesUseCaseCoroutineContext(): CoroutineContext =
        Dispatchers.Default + CoroutineName("Use Case")

    @Provides
    @RepositoryCoroutineContext
    fun provideRepositoryCoroutineContext(): CoroutineContext =
        Dispatchers.IO + CoroutineName("Repository")

    @Provides
    @DataSourceCoroutineContext
    fun provideDataSourceCoroutineContext(): CoroutineContext =
        Dispatchers.IO + CoroutineName("DataSource")
}
