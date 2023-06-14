package com.icarie.base.ui.home

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
import androidx.compose.ui.Modifier
import com.icarie.base.ui.compose.UIScreen
import com.icarie.base.ui.compose.composables.Title
import com.icarie.base.ui.compose.composables.UIStateView
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.states.UIState

@Composable
fun HomeScreen(uiState: UIState<HomeScreenUIData>) {
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
