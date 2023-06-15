package com.icarie.domain.album

import androidx.paging.PagingData
import com.icarie.domain.common.Status
import com.icarie.domain.models.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetAlbumsAsFlowUseCase {

    operator fun invoke(): Flow<Status<Flow<PagingData<Album>>>>
}

class DefaultGetAlbumsAsFlowUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetAlbumsAsFlowUseCase {

    override operator fun invoke(): Flow<Status<Flow<PagingData<Album>>>> =
        flow { emit(albumRepository.getAlbums()) }
}
