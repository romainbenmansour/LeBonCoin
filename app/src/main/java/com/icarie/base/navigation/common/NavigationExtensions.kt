package com.icarie.base.navigation.common

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.dialog
import com.icarie.base.navigation.Graph
import com.icarie.base.navigation.Screen
import com.icarie.base.utils.findActivity
import com.icarie.base.utils.screenHeight
import com.icarie.base.utils.screenWidth
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.navigation(
    graph: Graph,
    start: Screen,
    builder: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = start.path,
        route = graph.name,
        builder = builder,
        enterTransition = graph.transitions.enterTransition,
        exitTransition = graph.transitions.exitTransition,
        popEnterTransition = graph.transitions.popEnterTransition,
        popExitTransition = graph.transitions.popExitTransition
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composable(
    screen: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable NavBackStackEntry.() -> Unit,
) {
    composable(
        route = screen.path,
        arguments = arguments,
        deepLinks = deepLinks,
        content = {
            content(it)
        },
        enterTransition = screen.transitions.enterTransition,
        exitTransition = screen.transitions.exitTransition,
        popEnterTransition = screen.transitions.popEnterTransition,
        popExitTransition = screen.transitions.popExitTransition
    )
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.bottomSheet(
    screen: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable NavBackStackEntry.() -> Unit,
) {
    bottomSheet(
        route = screen.path,
        arguments = arguments,
        deepLinks = deepLinks,
        content = {
            content(it)
        },
    )
}

fun NavGraphBuilder.dialog(
    screen: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable () -> Unit,
) {
    dialog(
        route = screen.path,
        arguments = arguments,
        deepLinks = deepLinks,
        dialogProperties = DialogProperties(
            securePolicy = SecureFlagPolicy.SecureOff,
            usePlatformDefaultWidth = false,
        ),
        content = {
            // Fix for edge to edge dialog
            val fullScreenSize =
                LocalContext.current.findActivity()?.let {
                    with(LocalDensity.current) {
                        DpSize(it.screenWidth.toDp(), it.screenHeight.toDp())
                    }
                }

            Box(
                modifier = Modifier
                    .let { modifier ->
                        fullScreenSize?.let {
                            modifier.then(Modifier.requiredSize(fullScreenSize))
                        } ?: modifier
                    },
            ) {
                content()
            }
        },
    )
}

fun NavBackStackEntry.getArgument(key: String): String {
    return arguments?.getString(key)
        ?: throw IllegalStateException("Cannot get route argument named $key")
}

fun NavController.navigateSingleTop(route: String, builder: NavOptionsBuilder.() -> Unit) {
    navigate(route) {
        builder()
        launchSingleTop = true
    }
}
