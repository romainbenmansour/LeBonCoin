package com.icarie.data.albums.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.icarie.domain.models.Album

@Dao
interface CachedAlbumDao {

    @Query("SELECT * FROM cachedAlbum")
    fun getAll(): List<CachedAlbum>

    @Query("SELECT COUNT(albumId) FROM cachedAlbum")
    fun getCount(): Int

    @Insert
    fun insertAll(cachedAlbums: List<CachedAlbum>)

    @Delete
    fun delete(cachedAlbum: CachedAlbum)

    @Query("SELECT * FROM cachedAlbum ORDER BY id DESC")
    fun pagingSource(): PagingSource<Int, CachedAlbum>
}