package com.icarie.base.app

import com.icarie.domain.network.NetworkController
import javax.inject.Inject

class ControllerManagerImpl @Inject constructor(
    private val networkController: NetworkController
) : ControllerManager {

    override fun start() {
        networkController.start()
    }
}
