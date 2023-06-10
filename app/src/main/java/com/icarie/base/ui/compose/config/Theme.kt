package com.icarie.base.ui.compose.config

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Shapes as MaterialShapes

@Composable
private fun lightColorPalette(): Colors = lightColors(
    primary = sky,
    primaryVariant = arctic,
    secondary = navy,
    background = cotton,
    surface = white,
    onPrimary = white,
    onSecondary = white,
    onBackground = midnight,
    onSurface = midnight,
    error = red,
    onError = white
)

@Composable
private fun darkColorPalette(): Colors = darkColors(
    primary = sky,
    primaryVariant = arctic,
    secondary = navy,
    background = midnight,
    surface = darkness,
    onPrimary = white,
    onSecondary = white,
    onBackground = cotton,
    onSurface = cotton,
    error = red,
    onError = white
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (() -> Unit)
) {
    val colors = if (darkTheme) {
        darkColorPalette()
    } else {
        lightColorPalette()
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = MaterialShapes(
            small = AppShapes.small,
            medium = AppShapes.medium,
            large = AppShapes.large,
        ),
        content = content
    )
}

val Colors.textPrimary: Color
    @Composable
    get() {
        return if (isLight) midnight else cotton
    }

val Colors.textSecondary: Color
    @Composable
    get() = steel

val Colors.textDisabled: Color
    @Composable
    get() {
        return if (isLight) pearl else shadow
    }

val Colors.textInverted: Color
    @Composable
    get() {
        return if (isLight) white else darkness
    }

val Colors.backgroundSecondary: Color
    @Composable
    get() {
        return if (isLight) white else darkness
    }

val Colors.iconPrimary: Color
    @Composable
    get() {
        return if (isLight) midnight else cotton
    }

val Colors.iconSecondary: Color
    @Composable
    get() {
        return if (isLight) steel else charcoal
    }

val Colors.iconDisabled: Color
    @Composable
    get() {
        return if (isLight) pearl else shadow
    }

val Colors.iconInverted: Color
    @Composable
    get() {
        return if (isLight) white else darkness
    }

val Colors.overlay: Color
    @Composable
    get() = black20

val Colors.bottomSheetOverlay: Color
    @Composable
    get() = black40

val Colors.primaryOverlay: Color
    @Composable
    get() = iconPrimary.copy(alpha = 0.8f)

val Colors.divider: Color
    @Composable
    get() {
        return if (isLight) cloud else grey
    }

val Colors.dividerSecondary: Color
    @Composable
    get() = smoke

