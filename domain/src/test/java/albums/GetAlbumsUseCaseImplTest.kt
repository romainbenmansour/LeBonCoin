package albums

import com.icarie.domain.albums.AlbumRepository
import com.icarie.domain.albums.GetAlbumsUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class GetAlbumsUseCaseImplTest {

    private lateinit var useCase: GetAlbumsUseCaseImpl

    @RelaxedMockK
    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetAlbumsUseCaseImpl(albumRepository)
    }

    @Test
    fun `test use case properly delegate albums retrieval to repository`() = runTest {
        useCase()
        coVerify(exactly = 1) { albumRepository.getAlbums() }
    }
}