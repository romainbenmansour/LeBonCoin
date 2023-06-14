package com.icarie.lbc.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class LBCApplication : Application() {

    @Inject
    lateinit var controllerManager: ControllerManager

    override fun onCreate() {
        super.onCreate()
        controllerManager.start()
    }
}