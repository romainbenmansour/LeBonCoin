package com.icarie.base

import timber.log.Timber

object TimberInitializer {
    fun start() {
        Timber.plant(Timber.DebugTree())
    }
}
