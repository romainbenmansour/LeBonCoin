package com.icarie.data.albums.remote

import com.icarie.domain.albums.Album
import retrofit2.Response
import retrofit2.http.GET

interface AlbumRetrofitService {
  
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): Response<List<Album>>
}