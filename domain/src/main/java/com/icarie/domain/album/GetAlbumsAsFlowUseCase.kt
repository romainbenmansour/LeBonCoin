package com.icarie.domain.album

import com.icarie.domain.common.dataOrNull
import com.icarie.domain.models.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetAlbumsAsFlowUseCase {
    suspend operator fun invoke(): Flow<List<Album>>
}

class DefaultGetAlbumsAsFlowUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetAlbumsAsFlowUseCase {

    override suspend operator fun invoke(): Flow<List<Album>> =
        flow { albumRepository.getAlbums().dataOrNull ?: emptyList() }
}
