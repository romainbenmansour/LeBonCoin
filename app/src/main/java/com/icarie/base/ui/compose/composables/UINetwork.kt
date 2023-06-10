package com.icarie.base.ui.compose.composables

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.icarie.base.ui.compose.composables.HorizontalDivider
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.config.green
import com.icarie.base.ui.compose.config.red
import com.icarie.base.R
import com.icarie.base.ui.main.TimeLimitedView
import kotlin.time.Duration.Companion.seconds

@Composable
fun NetworkStateModal(
    networkState: Boolean,
) {
    Column(
        modifier = Modifier.animateContentSize(
            animationSpec = TweenSpec(
                NetworkUIConstants.ANIMATION_DURATION,
                NetworkUIConstants.ANIMATION_DELAY,
                FastOutSlowInEasing
            )
        )
    ) {
        Crossfade(
            animationSpec = tween(
                durationMillis = NetworkUIConstants.ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            ),
            targetState = networkState,
        ) { state ->
            when {
                !state -> {
                    Column {
                        LostConnectionView()
                        HorizontalDivider(modifier = Modifier.height(NetworkUIConstants.DIVIDER_HEIGHT))
                    }
                }
                else -> DismissibleRestoredConnectionView()
            }
        }
    }
}

@Composable
fun LostConnectionView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(NetworkUIConstants.BANNER_HEIGHT)
            .background(MaterialTheme.colors.surface),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OfflineCircle()
        Spacer(modifier = Modifier.width(AppSpacings.S))
        Text(
            text = stringResource(
                id = R.string.errormessage_title_appisoffline,
                stringResource(id = R.string.app_name_short)
            ),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun OfflineCircle() {
    val color = red
    Canvas(
        modifier = Modifier.size(NetworkUIConstants.CIRCLE_SIZE),
        onDraw = {
            drawCircle(color)
        }
    )
}

@Composable
fun DismissibleRestoredConnectionView() {
    TimeLimitedView(
        duration = NetworkUIConstants.DISMISS_DELAY
    ) {
        Column {
            RestoredConnectionView()
            HorizontalDivider(modifier = Modifier.height(NetworkUIConstants.DIVIDER_HEIGHT))
        }
    }
}

@Composable
private fun RestoredConnectionView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(NetworkUIConstants.BANNER_HEIGHT)
            .background(green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.errormessage_title_connectionrestored),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

private object NetworkUIConstants {
    const val ANIMATION_DURATION = 500
    const val ANIMATION_DELAY = 0

    val DISMISS_DELAY = 2.seconds

    val CIRCLE_SIZE = 8.dp
    val BANNER_HEIGHT = 26.dp
    val DIVIDER_HEIGHT = 1.dp
}
