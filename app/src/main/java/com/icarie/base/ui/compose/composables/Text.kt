package com.icarie.base.ui.compose.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.icarie.base.ui.compose.util.addEmptyLines
import com.icarie.base.ui.compose.config.AppSpacings.none
import com.icarie.base.ui.compose.config.textPrimary
import com.icarie.base.ui.compose.config.textSecondary

@Composable
fun Title(
    title: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.textPrimary,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = title,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.clickable(
            enabled = onClick != null,
            onClick = {
                onClick?.invoke()
            }
        ),
        textAlign = textAlign,
    )
}

@Composable
fun SubTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title.addEmptyLines(UIConstants.MAX_LINE - 1),
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.textSecondary,
        maxLines = UIConstants.MAX_LINE,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.padding(top = none)
    )
}

@Composable
fun TimeText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.textSecondary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

private object UIConstants {
    const val MAX_LINE = 2
}
