package albums

import com.icarie.domain.albums.AlbumRepository
import com.icarie.domain.albums.GetPagedAlbumsUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class GetPagedAlbumsUseCaseImplTest {

    private lateinit var useCase: GetPagedAlbumsUseCaseImpl

    @RelaxedMockK
    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetPagedAlbumsUseCaseImpl(albumRepository)
    }

    @Test
    fun `test use case properly requests repository for paged album flow`() {
        useCase()
        verify(exactly = 1) { albumRepository.getAlbumsAsFlow() }
    }
}