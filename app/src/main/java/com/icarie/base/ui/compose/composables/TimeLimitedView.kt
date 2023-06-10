package com.icarie.base.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Composable
fun TimeLimitedView(
    duration: Duration,
    content: @Composable () -> Unit,
) {
    var isVisible by remember { mutableStateOf(true) }
    if (isVisible) content()
    LaunchedEffect(Unit) {
        delay(duration)
        isVisible = false
    }
}
