package com.icarie.data.albums.remote

import com.icarie.data.albums.LocalAlbumDataSource
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

    override suspend fun getAlbums(pageSize: Int): Status<Unit> =
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

    override fun getAlbumsAsFlow() = localAlbumDataSource.getPagingSource(AlbumRepository.PAGE_SIZE)
}