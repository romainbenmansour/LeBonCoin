package com.icarie.lbc.app

import com.icarie.domain.network.NetworkRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class ControllerManagerImplTest {

    private lateinit var controllerManager: ControllerManagerImpl

    @RelaxedMockK
    private lateinit var networkRepository: NetworkRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        controllerManager = ControllerManagerImpl(networkRepository)
    }

    @Test
    fun `test network controller is properly started upon manager start`() {
        controllerManager.start()

        verify(exactly = 1) { networkRepository.start() }
    }
}