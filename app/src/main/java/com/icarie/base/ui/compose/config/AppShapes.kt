package com.icarie.base.ui.compose.config

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.icarie.base.ui.compose.config.AppRadius

object AppShapes {
    val small = RoundedCornerShape(AppRadius.X2S)
    val medium = RoundedCornerShape(AppRadius.X2S)
    val large = RoundedCornerShape(AppRadius.none)

    val cards = RoundedCornerShape(AppRadius.XS)

    val bottomSheetFullScreen = RoundedCornerShape(AppRadius.none)

    val bottomSheet = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp
    )
}
