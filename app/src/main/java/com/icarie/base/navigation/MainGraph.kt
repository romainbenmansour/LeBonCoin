package com.icarie.base.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.icarie.base.app.MainViewModel
import com.icarie.base.navigation.common.composable
import com.icarie.base.navigation.common.navigation
import com.icarie.base.ui.compose.UIScreen
import com.icarie.base.ui.compose.composables.Title
import com.icarie.base.ui.compose.composables.UIStateView
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.states.UIState
import com.icarie.base.ui.home.MainScreenUIData

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
        UIStateView(state = uiState) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(AppSpacings.M),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Title(
                            title = "Home Screen",
                            style = MaterialTheme.typography.h1,
                            modifier = Modifier.statusBarsPadding()
                        )

                        Spacer(modifier = Modifier.height(AppSpacings.L))
                    }

                    items(count = 100) {
                        Title(
                            title = "Item $it",
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }
                }
            }
        }
    }
}
