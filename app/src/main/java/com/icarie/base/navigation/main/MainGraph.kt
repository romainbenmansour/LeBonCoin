package com.icarie.base.navigation.main

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.icarie.base.navigation.Graph
import com.icarie.base.navigation.Screen
import com.icarie.base.navigation.common.composable
import com.icarie.base.navigation.common.navigation
import com.icarie.base.ui.home.HomeScreen
import com.icarie.base.ui.home.HomeViewModel

fun NavGraphBuilder.homeGraph(graph: Graph) {
    navigation(graph = graph, start = Screen.Home) {
        homeScreen()
    }
}

private fun NavGraphBuilder.homeScreen() {
    composable(Screen.Home) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val uiState by homeViewModel.uiStateData.collectAsState()
        HomeScreen(
            uiState = uiState
        )
    }
}
