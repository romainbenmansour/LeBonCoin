package com.icarie.base.navigation.splash

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.icarie.base.navigation.Graph
import com.icarie.base.navigation.Screen
import com.icarie.base.navigation.common.composable
import com.icarie.base.navigation.common.navigation
import com.icarie.base.ui.splash.SplashScreen
import com.icarie.base.ui.splash.SplashScreenViewModel

fun NavGraphBuilder.splashGraph(graph: Graph) {
    navigation(
        graph = graph,
        start = Screen.Splash
    ) {
        splashScreen()
    }
}

private fun NavGraphBuilder.splashScreen() {
    composable(Screen.Splash) {
        val viewModel = hiltViewModel<SplashScreenViewModel>()

        LaunchedEffect(Unit) {
            viewModel.start()
        }

        SplashScreen()
    }
}
