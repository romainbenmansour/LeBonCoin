package com.icarie.domain.network

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class GetNetworkStateUpdatesAsFlowUseCaseImplTest {

    private lateinit var getNetworkStateUpdatesAsFlowUseCase: GetNetworkStateUpdatesAsFlowUseCaseImpl

    @RelaxedMockK
    private lateinit var networkController: NetworkController

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getNetworkStateUpdatesAsFlowUseCase = GetNetworkStateUpdatesAsFlowUseCaseImpl(networkController)
    }

    @Test
    fun `test update flow is properly requested from network repository`() {
        getNetworkStateUpdatesAsFlowUseCase.invoke()

        verifyOnce { networkController.networkStateFlow }
    }
}

fun verifyOnce(block: () -> Unit) {
    verify(exactly = 1) { block() }
}