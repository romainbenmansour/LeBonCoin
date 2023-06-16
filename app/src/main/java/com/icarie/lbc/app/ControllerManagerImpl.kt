package com.icarie.lbc.app

import com.icarie.lbc.TimberInitializer
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
