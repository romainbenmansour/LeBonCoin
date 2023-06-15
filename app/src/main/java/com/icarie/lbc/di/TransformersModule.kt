package com.icarie.lbc.di

import com.icarie.lbc.ui.albums.AlbumTransformer
import com.icarie.lbc.ui.albums.AlbumTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface TransformersModule {

    @Binds
    fun bindAlbumTransformer(impl: AlbumTransformerImpl): AlbumTransformer
}
