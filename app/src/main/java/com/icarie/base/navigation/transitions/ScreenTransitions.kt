@file:OptIn(ExperimentalAnimationApi::class)

package com.icarie.base.navigation.transitions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry

typealias EnterTransitionBlock = AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition

typealias ExitTransitionBlock = AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition

interface ScreenTransitions {
    val enterTransition: EnterTransitionBlock
    val exitTransition: ExitTransitionBlock
    val popEnterTransition: EnterTransitionBlock
    val popExitTransition: ExitTransitionBlock
}
