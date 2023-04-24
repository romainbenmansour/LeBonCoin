package com.icarie.base.app

import com.icarie.domain.network.NetworkController
import javax.inject.Inject

class ControllerManagerImpl @Inject constructor() : ControllerManager {

    @Inject
    lateinit var networkController: NetworkController

    override fun start() {
        startControllers()
    }

    private fun startControllers() {
        networkController.start()
    }
}
