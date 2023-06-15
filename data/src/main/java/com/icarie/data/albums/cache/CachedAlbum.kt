package com.icarie.data.albums.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icarie.domain.models.Album

@Entity
data class CachedAlbum(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

fun Album.toCachedAlbum() = CachedAlbum(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)

fun CachedAlbum.toAlbum() = Album(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)
