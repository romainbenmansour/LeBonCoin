package com.icarie.base.ui.compose.config

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object AppSpacings {

    val statusBar
        @Composable get() = WindowInsets.statusBars
            .asPaddingValues()
            .calculateTopPadding()

    val navigationBar
        @Composable get() = WindowInsets.navigationBars
            .asPaddingValues()
            .calculateBottomPadding()

    val none get() = 0.dp
    val X3S get() = 2.dp
    val X2S get() = 4.dp
    val XS get() = 8.dp
    val S get() = 12.dp
    val M get() = 16.dp
    val L get() = 20.dp
    val XL get() = 24.dp
    val X2L get() = 32.dp
    val X3L get() = 40.dp
    val X4L get() = 56.dp
    val X5L get() = 64.dp
}
