package com.icarie.base.app

import com.icarie.base.TimberInitializer
import com.icarie.domain.network.NetworkRepository
import javax.inject.Inject

class ControllerManagerImpl @Inject constructor(
    private val networkRepository: NetworkRepository
) : ControllerManager {

    override fun start() {
        networkRepository.start()
        TimberInitializer.start()
    }
}
