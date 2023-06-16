package com.icarie.domain.albums

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPagedAlbumsUseCase {

    operator fun invoke(): Flow<PagingData<Album>>
}

class GetPagedAlbumsUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetPagedAlbumsUseCase {

    override operator fun invoke(): Flow<PagingData<Album>> =
        albumRepository.getAlbumsAsFlow()
}
