package com.icarie.base.ui.compose.composables

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.config.AppTheme
import com.icarie.base.R

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    titleColor: Color = MaterialTheme.colors.onSecondary,
    @FloatRange(
        from = 0.0,
        to = 1.0,
        fromInclusive = true,
        toInclusive = true
    ) backgroundAlpha: Float = 1f,
    @FloatRange(
        from = 0.0,
        to = 1.0,
        fromInclusive = true,
        toInclusive = true
    ) backBackgroundAlpha: Float = 1 - backgroundAlpha,
    @FloatRange(
        from = 0.0,
        to = 1.0,
        fromInclusive = true,
        toInclusive = true
    ) textAlpha: Float = 1f,
    onBack: (() -> Unit)? = null,
    trailingActions: (@Composable RowScope.() -> Unit)? = null
) {
    var rightWidth by remember { mutableStateOf(0) }
    val rightWidthDp = with(LocalDensity.current) { rightWidth.toDp() }

    val leftWidthDp = 40.dp.takeIf { onBack != null } ?: 0.dp

    val spacerLeft = (rightWidthDp - leftWidthDp).takeIf { it > 0.dp }
    val spacerRight = (leftWidthDp - rightWidthDp).takeIf { it > 0.dp }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(backgroundAlpha)
                .background(backgroundColor)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppSpacings.XS),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacings.S)
        ) {
            if (onBack != null) {
                UITopAppBarButton(
                    modifier = Modifier
                        .size(40.dp),
                    onClick = onBack,
                    backgroundAlpha = backBackgroundAlpha,
                    backgroundElevation = if (backgroundAlpha >= 0f) 0.dp else 5.dp
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_top_bar_back_button_icon),
                        contentDescription = null
                    )
                }
            }

            spacerLeft?.let {
                Spacer(modifier = Modifier.width(it))
            }

            UITopAppBarTitle(
                modifier = Modifier
                    .weight(1f)
                    .alpha(textAlpha),
                title = title,
                titleColor = titleColor
            )

            spacerRight?.let {
                Spacer(modifier = Modifier.width(it))
            }

            trailingActions?.let { actions ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(AppSpacings.XS),
                    modifier = Modifier
                        .fillMaxHeight()
                        .onSizeChanged {
                            rightWidth = kotlin.math.max(rightWidth, it.width)
                        },
                    content = actions
                )
            }
        }
    }
}

@Composable
fun UITopAppBarButton(
    modifier: Modifier,
    onClick: () -> Unit,
    backgroundAlpha: Float,
    backgroundElevation: Dp,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center,
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .shadow(elevation = backgroundElevation)
                    .alpha(backgroundAlpha)
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = CircleShape,
                    )
            )

            content()
        }
    }
}

@Composable
private fun UITopAppBarTitle(
    modifier: Modifier,
    title: String,
    titleColor: Color,
) {
    Text(
        text = title,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.body1,
        color = titleColor,
        maxLines = 1,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun UITopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    subTitle: String = "",
    leftAction: () -> Unit,
    subTitleAction: (() -> Unit)? = null,
    rightContent: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier
            .displayCutoutPadding()
            .fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            IconButton(
                onClick = leftAction,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .wrapContentHeight()
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_dismiss),
                    tint = colorResource(id = R.color.white),
                    contentDescription = "back"
                )
            }

            TextTopAppBar(
                title = title,
                subTitle = subTitle,
                onClick = subTitleAction,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center)
                    .fillMaxWidth(0.6f)
            )

            Row(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                rightContent()
            }
        }
    }
}

@Composable
fun TextTopAppBar(
    title: String = "",
    subTitle: String = "",
    modifier: Modifier,
    onClick: (() -> Unit)?
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        if (title.isNotEmpty()) {
            Title(
                title = title,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                onClick = onClick
            )
        }

        if (subTitle.isNotEmpty()) {
            Title(
                title = subTitle,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                onClick = onClick
            )
        }
    }
}

@Preview("UITopAppBar")
@Composable
fun PreviewUITopAppBar() {
    AppTheme {
        UITopAppBar(
            title = "Morning yoga",
            subTitle = "Yoga for beginners",
            leftAction = {},
            rightContent = {
                Surface(
                    modifier = Modifier
                        .width(90.dp)
                        .height(24.dp),
                    color = Color.Red
                ) {

                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    AppTheme {
        TopBar(
            title = "Hello",
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            onBack = {},
        )
    }
}
