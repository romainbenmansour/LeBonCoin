package com.icarie.base.ui.compose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icarie.base.ui.compose.config.AppTheme

@Composable
@Preview
fun PreviewEnabledStyledButton() {
    AppTheme {
        StyledButton(
            text = "Action!",
            state = ButtonState.ENABLED,
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
fun PreviewDisabledStyledButton() {
    AppTheme {
        StyledButton(
            text = "Disabled",
            state = ButtonState.DISABLED,
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
fun PreviewEnabledStyledOutlinedButton() {
    AppTheme {
        StyledOutlinedButton(
            text = "Action!",
            state = ButtonState.ENABLED,
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
fun PreviewDisabledStyledOutlinedButton() {
    AppTheme {
        StyledOutlinedButton(
            text = "Disabled",
            state = ButtonState.ENABLED,
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
private fun BackButtonPreview() {
    AppTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        ) {
            BackButton(
                Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp)
            ) { }
        }
    }
}