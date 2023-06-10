package com.icarie.base.ui.compose.util

import android.graphics.RectF
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

val RectF.topLeft: Offset get() = Offset(x = left, y = top)
val RectF.size: Size get() = Size(width(), height())
