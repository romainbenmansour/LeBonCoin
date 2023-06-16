package utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class TestWithCoroutine {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val testDispatcher = StandardTestDispatcher()

    protected val unconfinedTestDispatcher = UnconfinedTestDispatcher()
}
