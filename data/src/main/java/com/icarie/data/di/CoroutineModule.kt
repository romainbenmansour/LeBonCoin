package com.icarie.data.di

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
annotation class NavigationServiceCoroutineContext

@Qualifier
annotation class DataSourceCoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @RepositoryCoroutineContext
    fun provideRepositoryCoroutineContext(): CoroutineContext =
        Dispatchers.IO + CoroutineName("Repository")

    @Provides
    @NavigationServiceCoroutineContext
    fun provideNavigationServiceCoroutineContext(): CoroutineContext =
        Dispatchers.Main + CoroutineName("Navigation Service")

    @Provides
    @DataSourceCoroutineContext
    fun provideDataSourceCoroutineContext(): CoroutineContext =
        Dispatchers.IO + CoroutineName("DataSource")
}
