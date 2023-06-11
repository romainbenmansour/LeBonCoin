package com.icarie.base.di

import com.icarie.base.ui.home.DefaultHomeScreenUIDataTransformer
import com.icarie.base.ui.home.HomeScreenUIDataTransformer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface TransformerModule {

    @Binds
    fun bindMainScreenUIDataTransformer(impl: DefaultHomeScreenUIDataTransformer): HomeScreenUIDataTransformer
}
