package com.icarie.base.ui.compose.util

import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle

/**
 * Use this modifier to set a minimum height to your TextView based on a number of lines
 * This is a workaround for as long as Google doesn't implement minLines
 * More info here : https://issuetracker.google.com/issues/122476634
 */
@Composable
fun Modifier.minLines(textStyle: TextStyle, numberOfLines: Int): Modifier {
    val height = with(LocalDensity.current) {
        (textStyle.fontSize * 4 / 3 * numberOfLines).toDp()
    }
    return this.heightIn(height)
}
