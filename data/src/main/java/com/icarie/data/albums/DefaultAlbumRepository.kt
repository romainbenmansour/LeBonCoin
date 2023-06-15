package com.icarie.data.albums

import androidx.paging.PagingData
import com.icarie.data.di.RepositoryCoroutineContext
import com.icarie.domain.album.AlbumRepository
import com.icarie.domain.common.Status
import com.icarie.domain.models.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DefaultAlbumRepository @Inject constructor(
    @RepositoryCoroutineContext private val coroutineContext: CoroutineContext,
    private val localAlbumDataSource: LocalAlbumDataSource,
    private val remoteAlbumDataSource: RemoteAlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums(pageSize: Int): Status<Flow<PagingData<Album>>> =
        withContext(coroutineContext) {
            if (!localAlbumDataSource.hasCache()) {
                remoteAlbumDataSource.fetchAlbums().apply {
                    when (this) {
                        is Status.Error -> return@withContext Status.Error(error)
                        is Status.Success -> localAlbumDataSource.cacheAlbums(data)
                    }
                }
            }
            return@withContext Status.Success(getAlbumsAsFlow())
        }

    private fun getAlbumsAsFlow() = localAlbumDataSource.getPagingSource(PAGE_SIZE)

    private companion object {
        const val PAGE_SIZE = 10
    }
}