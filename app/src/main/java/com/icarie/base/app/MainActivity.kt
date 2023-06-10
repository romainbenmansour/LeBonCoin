package com.icarie.base.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.icarie.base.di.GRAPH_FOR_YOU
import com.icarie.base.navigation.GraphBuilder
import com.icarie.base.navigation.common.MutableNavigationManager
import com.icarie.base.navigation.common.NavigationManager
import com.icarie.base.ui.compose.UIScreen
import com.icarie.base.ui.compose.composables.NetworkStateModal
import com.icarie.base.ui.compose.composables.UIStateView
import com.icarie.base.ui.main.MainActivityScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterialApi::class)
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationManager: MutableNavigationManager

    @Inject
    lateinit var graphBuilder: GraphBuilder

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val sheetState = rememberModalBottomSheetState(
                ModalBottomSheetValue.Hidden,
                skipHalfExpanded = true,
            )

            val bottomSheetNavigator = remember(sheetState) {
                BottomSheetNavigator(sheetState = sheetState)
            }

            val navController = rememberAnimatedNavController(bottomSheetNavigator)
                .apply {
                    navigationManager.assignNavController(this)
                }

            MainActivityScreen(
                navController = navController,
                bottomSheetNavigator = bottomSheetNavigator,
                modalBottomSheetState = sheetState,
                startGraph = GRAPH_FOR_YOU,
                graphBuilder = graphBuilder,
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationManager.clearNavController()
    }
}
