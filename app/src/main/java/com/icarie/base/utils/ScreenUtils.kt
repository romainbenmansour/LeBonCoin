package com.icarie.base.utils

import android.app.Activity
import android.os.Build

inline val Activity.screenHeight: Int
    get() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            windowManager.currentWindowMetrics.bounds.height()
        } else {
            resources.displayMetrics.heightPixels
        }

inline val Activity.screenWidth: Int
    get() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            windowManager.currentWindowMetrics.bounds.width()
        } else {
            resources.displayMetrics.widthPixels
        }
