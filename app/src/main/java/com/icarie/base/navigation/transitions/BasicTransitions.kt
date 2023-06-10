@file:OptIn(ExperimentalAnimationApi::class)

package com.icarie.base.navigation.transitions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Stable

@Stable
fun <T> popEnterTween(): TweenSpec<T> =
    TweenSpec(
        durationMillis = AnimationConstants.DefaultDurationMillis,
        delay = AnimationConstants.DefaultDurationMillis / 2,
        easing = FastOutSlowInEasing
    )

object BasicTransitions {

    data class None(
        override val enterTransition: EnterTransitionBlock = { EnterTransition.None },
        override val exitTransition: ExitTransitionBlock = { ExitTransition.None },
        override val popEnterTransition: EnterTransitionBlock = { EnterTransition.None },
        override val popExitTransition: ExitTransitionBlock = { ExitTransition.None }
    ) : ScreenTransitions

    data class Fade(
        override val enterTransition: EnterTransitionBlock = { fadeIn() },
        override val exitTransition: ExitTransitionBlock = { fadeOut() },
        override val popEnterTransition: EnterTransitionBlock = { fadeIn(popEnterTween()) },
        override val popExitTransition: ExitTransitionBlock = { fadeOut() }
    ) : ScreenTransitions

    data class SlideInHorizontal(
        override val enterTransition: EnterTransitionBlock = { slideInHorizontally { it } },
        override val exitTransition: ExitTransitionBlock = { slideOutHorizontally { -it } },
        override val popEnterTransition: EnterTransitionBlock = { slideInHorizontally(popEnterTween()) { -it } },
        override val popExitTransition: ExitTransitionBlock = { slideOutHorizontally { it } }
    ) : ScreenTransitions

    data class SlideInVertical(
        override val enterTransition: EnterTransitionBlock = { slideInVertically { it } },
        override val exitTransition: ExitTransitionBlock = { slideOutVertically { -it } },
        override val popEnterTransition: EnterTransitionBlock = { slideInVertically(popEnterTween()) { -it } },
        override val popExitTransition: ExitTransitionBlock = { slideOutVertically { it } }
    ) : ScreenTransitions

    data class SlideInFadeOutHorizontal(
        override val enterTransition: EnterTransitionBlock = { slideInHorizontally { it } },
        override val exitTransition: ExitTransitionBlock = { ExitTransition.None },
        override val popEnterTransition: EnterTransitionBlock = { fadeIn(popEnterTween()) },
        override val popExitTransition: ExitTransitionBlock = { slideOutHorizontally { it } }
    ) : ScreenTransitions

    data class SlideInVerticalAndFade(
        override val enterTransition: EnterTransitionBlock = { slideInVertically { it } + fadeIn() },
        override val exitTransition: ExitTransitionBlock = { slideOutVertically { -it } + fadeOut() },
        override val popEnterTransition: EnterTransitionBlock = { slideInVertically(popEnterTween()) { -it }  + fadeIn()},
        override val popExitTransition: ExitTransitionBlock = { slideOutVertically { it } + fadeOut() }
    ) : ScreenTransitions
}
