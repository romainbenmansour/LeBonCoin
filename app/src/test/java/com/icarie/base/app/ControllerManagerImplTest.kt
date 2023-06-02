package com.icarie.base.app

import com.icarie.domain.network.NetworkController
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

internal class ControllerManagerImplTest {

    private lateinit var controllerManager: ControllerManagerImpl

    @RelaxedMockK
    private lateinit var networkController: NetworkController

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        controllerManager = ControllerManagerImpl(networkController)
    }

    @Test
    fun `test network controller is properly started upon manager start`() {
        controllerManager.start()

        verify(exactly = 1) { networkController.start() }
    }
}