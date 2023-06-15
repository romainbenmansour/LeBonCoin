package com.icarie.data.albums

import com.icarie.data.di.RepositoryCoroutineContext
import com.icarie.domain.album.AlbumRepository
import com.icarie.domain.common.Status
import com.icarie.domain.models.Album
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DefaultAlbumRepository @Inject constructor(
    @RepositoryCoroutineContext private val coroutineContext: CoroutineContext,
    private val localAlbumDataSource: LocalAlbumDataSource,
    private val remoteAlbumDataSource: RemoteAlbumDataSource
) : AlbumRepository {

    // First shot with no pagination
    override suspend fun getAlbums(): Status<List<Album>> =
        withContext(coroutineContext) {
            if (!localAlbumDataSource.hasCache()) {
                remoteAlbumDataSource.fetchAlbums().apply {
                    when (this) {
                        is Status.Error -> return@withContext this
                        is Status.Success -> localAlbumDataSource.cacheAlbums(data)
                    }
                }
            }
            return@withContext Status.Success(localAlbumDataSource.getAll())
        }
}