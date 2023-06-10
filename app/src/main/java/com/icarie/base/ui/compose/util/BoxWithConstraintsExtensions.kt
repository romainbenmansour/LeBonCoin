package com.icarie.base.ui.compose.util

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity

val BoxWithConstraintsScope.center: Offset
    @Composable
    get() = with(LocalDensity.current) {
        Offset(x = minWidth.toPx() / 2, y = minHeight.toPx() / 2)
    }