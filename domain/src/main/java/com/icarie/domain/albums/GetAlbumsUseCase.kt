package com.icarie.domain.albums

import com.icarie.domain.common.Status
import javax.inject.Inject

interface GetAlbumsUseCase {

    suspend operator fun invoke(): Status<Unit>
}

class DefaultGetAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetAlbumsUseCase {

    override suspend operator fun invoke(): Status<Unit> =
        albumRepository.getAlbums()
}
