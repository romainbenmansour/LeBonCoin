package com.icarie.base.ui.compose.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.icarie.base.ui.compose.config.divider

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.divider
) {
    Divider(
        color = color,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.divider
) {
    Divider(
        color = color,
        modifier = modifier.fillMaxHeight()
    )
}

/**
 * Divider should never be used directly but only through
 * @VerticalDivider
 * @HorizontalDivider
 * To avoid error prone behavior cause the width / height of the divider has not been provided
 */
@Composable
private fun Divider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.divider
) {
    Surface(
        color = color,
        modifier = modifier
    ) { }
}
