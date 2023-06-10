package com.icarie.base.ui.compose.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.icarie.base.ui.compose.config.AppRadius
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.config.TestTag
import com.icarie.base.ui.compose.config.textInverted
import com.icarie.base.ui.compose.config.textPrimary

enum class ButtonState {
    ENABLED,
    DISABLED,
    LOADING;
}

@Composable
fun StyledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: ButtonState = ButtonState.ENABLED,
    icon: (@Composable RowScope.() -> Unit)? = null,
    colors: @Composable (Boolean) -> ButtonColors = { ButtonStyle.buttonColors(it) },
    buttonTestTag: String = TestTag.Generics.BUTTON,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    ButtonWithoutRippleEffect(
        modifier = modifier.height(ButtonStyle.BUTTON_HEIGHT_DP),
        shape = RoundedCornerShape(ButtonStyle.BUTTON_BORDER_RADIUS_DP),
        contentPadding = ButtonStyle.BUTTON_CONTENT_PADDING,
        enabled = state == ButtonState.ENABLED,
        colors = colors(isPressed),
        elevation = ButtonStyle.buttonElevation(),
        interactionSource = interactionSource,
        onClick = onClick,
        buttonTestTag = buttonTestTag
    ) {
        Content(
            state = state,
            icon = icon,
            text = text,
        )
    }
}

@Composable
private fun RowScope.Content(
    state: ButtonState,
    icon: (@Composable RowScope.() -> Unit)? = null,
    text: String,
) {
    if (state == ButtonState.LOADING) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(ButtonStyle.LOADER_HEIGHT)
                .align(Alignment.CenterVertically),
            color = MaterialTheme.colors.surface
        )
    } else {
        if (icon != null) {
            icon()
            Spacer(modifier = Modifier.width(AppSpacings.M))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun StyledOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: ButtonState = ButtonState.ENABLED,
    icon: (@Composable RowScope.() -> Unit)? = null
) {
    Button(
        modifier = modifier.height(ButtonStyle.BUTTON_HEIGHT_DP),
        shape = RoundedCornerShape(ButtonStyle.BUTTON_BORDER_RADIUS_DP),
        contentPadding = ButtonStyle.BUTTON_CONTENT_PADDING,
        enabled = state == ButtonState.ENABLED,
        colors = ButtonStyle.outlinedButtonColors(),
        elevation = ButtonStyle.buttonElevation(),
        border = ButtonStyle.outlinedButtonStroke(),
        onClick = onClick
    ) {
        Content(
            state = state,
            icon = icon,
            text = text,
        )
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: ButtonState = ButtonState.ENABLED,
    icon: (@Composable RowScope.() -> Unit)? = null,
    contentPadding: PaddingValues = ButtonStyle.BUTTON_CONTENT_PADDING
) {
    Button(
        modifier = modifier.height(ButtonStyle.BUTTON_HEIGHT_DP),
        shape = RoundedCornerShape(ButtonStyle.BUTTON_BORDER_RADIUS_DP),
        contentPadding = contentPadding,
        enabled = state == ButtonState.ENABLED,
        colors = ButtonStyle.textButtonColors(),
        elevation = ButtonStyle.buttonElevation(),
        onClick = onClick,
    ) {
        Content(
            state = state,
            icon = icon,
            text = text,
        )
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    onBack: () -> Unit
) {
    IconButton(onClick = onBack, modifier = modifier) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            tint = color
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ButtonWithoutRippleEffect(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    buttonTestTag: String = TestTag.Generics.BUTTON,
    content: @Composable RowScope.() -> Unit,
) {
    val contentColor by colors.contentColor(enabled)

    Surface(
        onClick = onClick,
        modifier = modifier.semantics { testTag = buttonTestTag },
        enabled = enabled,
        shape = shape,
        color = colors.backgroundColor(enabled).value,
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
        elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
        interactionSource = interactionSource
    ) {
        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
            ProvideTextStyle(
                value = MaterialTheme.typography.button
            ) {
                Row(
                    Modifier
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight
                        )
                        .padding(contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}

object ButtonStyle {

    val LOADER_MARGIN = AppSpacings.S
    val BUTTON_BORDER_RADIUS_DP = AppRadius.X6L
    val BUTTON_CONTENT_PADDING = PaddingValues(horizontal = AppSpacings.X2L)
    val BUTTON_HEIGHT_DP = 50.dp
    val OUTLINED_BUTTON_BORDER_WIDTH_DP = 2.dp
    val LOADER_HEIGHT = BUTTON_HEIGHT_DP - LOADER_MARGIN - LOADER_MARGIN

    private const val DISABLED_ALPHA = .5f

    @Composable
    fun buttonColors(isPressed: Boolean) = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colors.textInverted,
        backgroundColor = if (!isPressed) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
        disabledBackgroundColor = MaterialTheme.colors.primaryVariant.copy(alpha = DISABLED_ALPHA),
        disabledContentColor = MaterialTheme.colors.textInverted.copy(alpha = DISABLED_ALPHA)
    )

    @Composable
    fun surfaceButtonColors() = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colors.textPrimary,
        backgroundColor = MaterialTheme.colors.surface,
        disabledBackgroundColor = MaterialTheme.colors.surface.copy(alpha = DISABLED_ALPHA),
        disabledContentColor = MaterialTheme.colors.textInverted.copy(alpha = DISABLED_ALPHA)
    )

    @Composable
    fun outlinedButtonColors() = ButtonDefaults.buttonColors(
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.secondary,
        disabledBackgroundColor = Color.Transparent,
        disabledContentColor = MaterialTheme.colors.primaryVariant.copy(alpha = DISABLED_ALPHA)
    )

    @Composable
    fun outlinedButtonStroke() = BorderStroke(
        width = OUTLINED_BUTTON_BORDER_WIDTH_DP,
        color = MaterialTheme.colors.primaryVariant
    )

    @Composable
    fun textButtonColors() = ButtonDefaults.buttonColors(
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.primary,
        disabledBackgroundColor = Color.Transparent,
        disabledContentColor = MaterialTheme.colors.primary.copy(alpha = DISABLED_ALPHA)
    )

    @Composable
    fun buttonElevation() = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
        disabledElevation = 0.dp
    )
}
