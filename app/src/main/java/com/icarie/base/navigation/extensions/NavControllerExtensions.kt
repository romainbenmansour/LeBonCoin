package com.icarie.base.navigation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import com.icarie.base.navigation.Screen
import kotlinx.collections.immutable.ImmutableList

interface BottomNavDestination {
    val path: String
}

@Composable
fun <T : BottomNavDestination> NavController.currentTabSelectedAsState(
    items: ImmutableList<T>,
): State<T> {
    val navBackStackEntry by currentBackStackEntryAsState()
    val state = remember {
        derivedStateOf {

            // The selected screen is a root destination
            val destinationIsRoot = items
                .firstOrNull {
                    it.path == navBackStackEntry?.destination?.route
                }

            // The selected screen is a nested destination
            // ↳ We select the deepest item in the backstack
            val lastSelectedItem = {
                items
                    .maxByOrNull { bottomItem ->
                        backQueue.indexOfLast {
                            it.destination.route == bottomItem.path
                        }
                    }
            }

            destinationIsRoot ?: lastSelectedItem() ?: items.first()
        }
    }
    return state
}

fun NavOptionsBuilder.popUpToForYouScreen() = popUpTo(Screen.Home.path)

fun NavOptionsBuilder.clearBackStack(navController: NavController?) = popUpTo(navController?.graph?.id ?: 0)