package com.icarie.lbc

import timber.log.Timber

object TimberInitializer {
    fun start() {
        Timber.plant(Timber.DebugTree())
    }
}
