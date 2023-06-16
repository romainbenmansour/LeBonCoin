package com.icarie.data.albums

import com.icarie.data.albums.cache.LocalAlbumDataSource
import com.icarie.data.albums.remote.RemoteAlbumDataSource
import com.icarie.data.di.RepositoryCoroutineContext
import com.icarie.domain.albums.AlbumRepository
import com.icarie.domain.common.Status
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DefaultAlbumRepository @Inject constructor(
    @RepositoryCoroutineContext private val coroutineContext: CoroutineContext,
    private val localAlbumDataSource: LocalAlbumDataSource,
    private val remoteAlbumDataSource: RemoteAlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums(): Status<Unit> =
        withContext(coroutineContext) {
            if (!localAlbumDataSource.hasCache()) {
                remoteAlbumDataSource.fetchAlbums().apply {
                    when (this) {
                        is Status.Error -> return@withContext Status.Error(error)
                        is Status.Success -> localAlbumDataSource.cacheAlbums(data)
                    }
                }
            }
            return@withContext Status.Success(Unit)
        }

    override fun getAlbumsAsFlow() = localAlbumDataSource.getPagingSource(PAGE_SIZE)

    companion object {
        const val PAGE_SIZE = 10
    }
}