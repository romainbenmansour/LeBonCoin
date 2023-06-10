package com.icarie.base.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.icarie.base.navigation.BottomNavItem
import com.icarie.base.navigation.GraphBuilder
import com.icarie.base.navigation.extensions.currentTabSelectedAsState
import com.icarie.base.ui.compose.UIScreen
import com.icarie.base.ui.compose.composables.NetworkStateModal
import com.icarie.base.ui.compose.config.AppShapes
import com.icarie.base.ui.compose.config.AppSpacings
import com.icarie.base.ui.compose.config.black40
import com.icarie.base.ui.compose.config.bottomSheetOverlay
import com.icarie.base.ui.compose.config.midnight
import com.icarie.domain.network.NetworkState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
)
@Composable
fun MainActivityScreen(
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    modalBottomSheetState: ModalBottomSheetState,
    networkState: NetworkState,
    graphBuilder: GraphBuilder,
    startGraph: String,
) {
    UIScreen {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedNavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = startGraph
            ) {
                graphBuilder.build(this)
            }

            NetworkStateModal(
                networkState = networkState == NetworkState.CONNECTED,
            )

            BottomContent(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
            )
        }

        BottomSheetContainer(
            bottomSheetNavigator = bottomSheetNavigator,
            modalBottomSheetState = modalBottomSheetState,
        )
    }
}

@Composable
private fun BottomContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val items = BottomNavItem.values().toList().toImmutableList()
    val tabSelected by navController.currentTabSelectedAsState(items)

    Column(
        modifier = modifier
    ) {
        BottomNav(
            items = items,
            currentItem = tabSelected,
            navController = navController,
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

@Composable
private fun BottomNav(
    items: ImmutableList<BottomNavItem>,
    currentItem: BottomNavItem,
    navController: NavController,
) {
    // Home route is the first tab
    val homeRoute = BottomNavItem.Home.path

    BottomNavigation(
        modifier = Modifier.height(MainActivityScreenConstants.BOTTOM_BAR_HEIGHT + AppSpacings.navigationBar),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier
                    .navigationBarsPadding(),
                icon = {
                    Icon(
                        modifier = Modifier.size(MainActivityScreenConstants.BOTTOM_NAV_ITEM_SIZE),
                        imageVector = ImageVector
                            .vectorResource(
                                id = when (item) {
                                    currentItem -> item.icon.selected
                                    else -> item.icon.unselected
                                }
                            ),
                        contentDescription = stringResource(id = item.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                    )
                },
                selectedContentColor = midnight,
                unselectedContentColor = black40,
                alwaysShowLabel = true,
                selected = item == currentItem,
                onClick = {
                    navController.navigate(item.graph) {
                        if (currentItem.path == item.path) {
                            popUpTo(item.path) {
                                saveState = false
                                inclusive = true
                            }
                            restoreState = false
                        } else {
                            popUpTo(homeRoute) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

private object MainActivityScreenConstants {
    val BOTTOM_NAV_ITEM_SIZE = 18.dp
    val BOTTOM_BAR_HEIGHT = 56.dp
}
