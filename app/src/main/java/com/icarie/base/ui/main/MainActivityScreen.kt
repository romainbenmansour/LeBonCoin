
package com.icarie.base.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.icarie.base.navigation.GraphBuilder
import com.icarie.base.ui.compose.UIScreen
import com.icarie.base.ui.compose.config.AppShapes
import com.icarie.base.ui.compose.config.bottomSheetOverlay

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
)
@Composable
fun MainActivityScreen(
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    modalBottomSheetState: ModalBottomSheetState,
    graphBuilder: GraphBuilder,
    startGraph: String,
) {
    UIScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedNavHost(
                modifier = Modifier
                    .weight(1f),
                navController = navController,
                startDestination = startGraph
            ) {
                graphBuilder.build(this)
            }
        }

        BottomSheetContainer(
            bottomSheetNavigator = bottomSheetNavigator,
            modalBottomSheetState = modalBottomSheetState,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
private fun BottomSheetContainer(
    bottomSheetNavigator: BottomSheetNavigator,
    modalBottomSheetState: ModalBottomSheetState,
) {
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = bottomSheetNavigator.sheetContent,
        modifier = Modifier.fillMaxSize(),
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetShape = AppShapes.bottomSheet,
        scrimColor = MaterialTheme.colors.bottomSheetOverlay
    ) {}
}
