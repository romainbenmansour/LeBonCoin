package com.icarie.data.albums.remote

import com.icarie.data.albums.DefaultAlbumRepository
import com.icarie.data.albums.cache.LocalAlbumDataSource
import com.icarie.domain.albums.Album
import com.icarie.domain.common.Status
import com.icarie.domain.errors.AppError
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import utils.TestWithCoroutine

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultAlbumRepositoryTest : TestWithCoroutine() {

    private lateinit var albumRepository: DefaultAlbumRepository

    @RelaxedMockK
    lateinit var remoteAlbumDataSource: RemoteAlbumDataSource

    @RelaxedMockK
    lateinit var localAlbumDataSource: LocalAlbumDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        albumRepository = DefaultAlbumRepository(
            coroutineContext = unconfinedTestDispatcher,
            localAlbumDataSource = localAlbumDataSource,
            remoteAlbumDataSource = remoteAlbumDataSource
        )
    }

    @Test
    fun `test repository properly requests local data source for paging`() {
        albumRepository.getAlbumsAsFlow()

        verify(exactly = 1) { localAlbumDataSource.getPagingSource(DefaultAlbumRepository.PAGE_SIZE) }
    }

    @Test
    fun `test already fetched content is not retrieved again`() = runTest(unconfinedTestDispatcher) {
        coEvery { localAlbumDataSource.hasCache() } returns true

        val status = albumRepository.getAlbums()

        coVerify(exactly = 0) { remoteAlbumDataSource.fetchAlbums() }

        Assert.assertTrue(status is Status.Success)
    }

    @Test
    fun `test failed content fetch does NOT cache it and returns error`() = runTest(unconfinedTestDispatcher) {
        coEvery { localAlbumDataSource.hasCache() } returns false
        coEvery { remoteAlbumDataSource.fetchAlbums() } returns Status.Error(AppError.Offline)

        val status = albumRepository.getAlbums()

        coVerify(exactly = 1) { remoteAlbumDataSource.fetchAlbums() }
        coVerify(exactly = 0) { localAlbumDataSource.cacheAlbums(any()) }

        Assert.assertTrue(status is Status.Error)
    }

    @Test
    fun `test successful content fetch IS CACHED and returns success`() = runTest(unconfinedTestDispatcher) {
        val fakeResults = fakeAlbumList()

        coEvery { localAlbumDataSource.hasCache() } returns false
        coEvery { remoteAlbumDataSource.fetchAlbums() } returns Status.Success(fakeResults)

        val status = albumRepository.getAlbums()

        coVerify(exactly = 1) { remoteAlbumDataSource.fetchAlbums() }
        coVerify(exactly = 1) { localAlbumDataSource.cacheAlbums(fakeResults) }

        Assert.assertTrue(status is Status.Success)
    }

    private fun fakeAlbumList(): List<Album> = listOf(
        Album(
            albumId = 1,
            id = 1,
            title = "title",
            url = "url",
            thumbnailUrl = "thumbnailUrl"
        )
    )
}