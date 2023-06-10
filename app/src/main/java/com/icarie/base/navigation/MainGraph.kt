package com.icarie.base.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.icarie.base.app.MainViewModel
import com.icarie.base.navigation.common.composable
import com.icarie.base.navigation.common.navigation
import com.icarie.base.ui.compose.UIScreen
import com.icarie.base.ui.compose.composables.NetworkStateModal
import com.icarie.base.ui.compose.composables.UIStateView
import com.icarie.base.ui.compose.states.UIState
import com.icarie.base.ui.main.MainScreenUIData

fun NavGraphBuilder.homeGraph(graph: Graph) {
    navigation(graph = graph, start = Screen.Home) {
        homeScreen()
    }
}

private fun NavGraphBuilder.homeScreen() {
    composable(Screen.Home) {
        val mainViewModel = hiltViewModel<MainViewModel>()
        val uiState by mainViewModel.uiStateData.collectAsState()
        MainScreen(
            uiState = uiState
        )
    }
}

@Composable
private fun MainScreen(uiState: UIState<MainScreenUIData>) {
    UIScreen {
        UIStateView(state = uiState) { data ->
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Cyan),
                contentAlignment = Alignment.BottomStart
            ) {
                NetworkStateModal(networkState = data.connected)
            }
        }
    }
}
