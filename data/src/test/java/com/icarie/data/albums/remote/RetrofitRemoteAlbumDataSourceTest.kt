package com.icarie.data.albums.remote

import com.icarie.domain.common.Status
import com.icarie.domain.errors.AppError
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import utils.TestWithCoroutine
import java.lang.IllegalStateException

@OptIn(ExperimentalCoroutinesApi::class)
class RetrofitRemoteAlbumDataSourceTest : TestWithCoroutine() {

    private lateinit var remoteAlbumDataSource: RetrofitRemoteAlbumDataSource

    @RelaxedMockK
    private lateinit var albumRetrofitService: AlbumRetrofitService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteAlbumDataSource = RetrofitRemoteAlbumDataSource(albumRetrofitService)
    }

    @Test
    fun `test remote data source properly requests retrofit for albums`() = runTest {
        remoteAlbumDataSource.fetchAlbums()

        coVerify(exactly = 1) { albumRetrofitService.getAlbums() }
    }

    @Test
    fun `test failed download returns an error`() = runTest {
        coEvery { albumRetrofitService.getAlbums() } returns Response.error(
            401, ResponseBody.create(null, String())
        )

        val status = remoteAlbumDataSource.fetchAlbums()

        coVerify(exactly = 1) { albumRetrofitService.getAlbums() }

        status.apply {
            Assert.assertTrue(status is Status.Error)
            Assert.assertTrue((status as Status.Error).error == AppError.Offline)
        }
    }

    @Test
    fun `test successful download returns success`() = runTest {
        coEvery { albumRetrofitService.getAlbums() } returns Response.success(null)

        val status = remoteAlbumDataSource.fetchAlbums()

        coVerify(exactly = 1) { albumRetrofitService.getAlbums() }

        Assert.assertTrue(status is Status.Success)
    }

    @Test
    fun `test exception thrown during fetch returns an error`() = runTest {
        coEvery { albumRetrofitService.getAlbums() } throws IllegalStateException()

        val status = remoteAlbumDataSource.fetchAlbums()

        coVerify(exactly = 1) { albumRetrofitService.getAlbums() }

        Assert.assertTrue(status is Status.Error)
        Assert.assertTrue((status as Status.Error).error == AppError.Offline)
    }
}