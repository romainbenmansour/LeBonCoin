package com.icarie.base.app

import android.app.Application
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application() {

    @Inject
    lateinit var controllerManager: ControllerManager

    override fun onCreate() {
        super.onCreate()
        controllerManager.start()
    }
}