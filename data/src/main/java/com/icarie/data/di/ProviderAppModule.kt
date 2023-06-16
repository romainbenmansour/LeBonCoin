package com.icarie.data.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.icarie.data.albums.remote.AlbumRetrofitService
import com.icarie.data.albums.cache.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ProviderAppModule {

    @Provides
    fun providesConnectivityManager(@ApplicationContext appContext: Context): ConnectivityManager =
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    fun providesGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    fun providesHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun providesAlbumRetrofitService(retrofit: Retrofit) = retrofit.create<AlbumRetrofitService>()

    @Provides
    fun providesAlbumsDataBase(application: Application) =
        Room.databaseBuilder(
            context = application,
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        ).build()

    @Provides
    fun providesAlbumsDao(appDatabase: AppDatabase) = appDatabase.cachedAlbumDao()

    companion object {
        private const val BASE_URL: String = "https://static.leboncoin.fr/"
        private const val DATABASE_NAME: String = "local-cache"
    }
}
