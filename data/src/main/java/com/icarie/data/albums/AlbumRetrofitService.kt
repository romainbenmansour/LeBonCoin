package com.icarie.data.albums

import com.icarie.domain.models.Album
import retrofit2.Response
import retrofit2.http.GET

interface AlbumRetrofitService {
  
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): Response<List<Album>>
}