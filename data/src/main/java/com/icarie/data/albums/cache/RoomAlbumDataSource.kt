package com.icarie.data.albums.cache

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.icarie.data.di.DataSourceCoroutineContext
import com.icarie.domain.albums.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface LocalAlbumDataSource {
    suspend fun hasCache(): Boolean
    fun cacheAlbums(data: List<Album>)
    fun getAll(): List<Album>

    fun getPagingSource(pageSize: Int): Flow<PagingData<Album>>
}

class RoomAlbumDataSource @Inject constructor(
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

    override fun getPagingSource(pageSize: Int): Flow<PagingData<Album>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { albumDao.pagingSource() },
        ).flow.map { pagingData ->
            pagingData.map { cached -> cached.toAlbum() }
        }
}