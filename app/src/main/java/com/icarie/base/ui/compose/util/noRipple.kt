package com.icarie.base.ui.compose.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

fun Modifier.noRipple(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) =
    clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    )