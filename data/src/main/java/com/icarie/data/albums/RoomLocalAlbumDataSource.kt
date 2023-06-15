package com.icarie.data.albums

import com.icarie.data.albums.cache.CachedAlbumDao
import com.icarie.data.albums.cache.toAlbum
import com.icarie.data.albums.cache.toCachedAlbum
import com.icarie.data.di.DataSourceCoroutineContext
import com.icarie.domain.models.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface LocalAlbumDataSource {
    suspend fun hasCache(): Boolean
    fun cacheAlbums(data: List<Album>)
    fun getAll(): List<Album>
}

class RoomLocalAlbumDataSource @Inject constructor(
    @DataSourceCoroutineContext private val coroutineContext: CoroutineContext,
    private val albumDao: CachedAlbumDao
) : LocalAlbumDataSource {

    private val coroutineScope = CoroutineScope(coroutineContext)

    override suspend fun hasCache(): Boolean =
        withContext(coroutineContext) { albumDao.getCount() > 0 }

    override fun cacheAlbums(data: List<Album>) {
        coroutineScope.launch {
            data.map { it.toCachedAlbum() }.apply {
                albumDao.insertAll(this)
            }
        }
    }

    override fun getAll(): List<Album> = albumDao.getAll().map { it.toAlbum() }
}