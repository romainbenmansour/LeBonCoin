package com.icarie.domain.album

interface AlbumRepository {
    suspend fun getAlbums(): Unit
}