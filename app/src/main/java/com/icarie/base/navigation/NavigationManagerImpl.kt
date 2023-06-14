package com.icarie.base.navigation

import androidx.annotation.VisibleForTesting
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.icarie.base.di.GRAPH_FOR_YOU
import com.icarie.base.navigation.common.MutableNavigationManager
import com.icarie.base.navigation.common.NavigationManager
import com.icarie.base.navigation.common.navigateSingleTop
import com.icarie.base.navigation.extensions.clearBackStack
import com.icarie.data.di.NavigationServiceCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NavigationManagerImpl @Inject constructor(
    @NavigationServiceCoroutineContext coroutineContext: CoroutineContext,
) : NavigationManager, MutableNavigationManager {

    private val coroutineScope = CoroutineScope(coroutineContext)
    private var navigationController: NavController? = null

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _routeUpdates: MutableStateFlow<Screen> = MutableStateFlow(Screen.None)

    override val screenUpdates: StateFlow<Screen>
        get() = _routeUpdates

    override fun goBack() {
        coroutineScope.launch {
            navigationController?.navigateUp()
        }
    }

    override fun assignNavController(navController: NavController) {
        navigationController = navController
        navigationController?.addOnDestinationChangedListener { _, destination, _ ->
            destination.route?.let { path ->
                Screen.values().firstOrNull { it.path == path }
            }?.let(::onScreenChanged)
        }
    }

    override fun clearNavController() {
        navigationController = null
    }

    override fun goToMainEntry() {
        navigateToScreen(GRAPH_FOR_YOU) {
            clearBackStack(navigationController)
        }
    }

    // Ensure we are requesting nav from main thread
    private fun navigateToScreen(path: String, builder: NavOptionsBuilder.() -> Unit = {}) {
        coroutineScope.launch {
            navigationController?.navigateSingleTop(route = path, builder = builder)
        }
    }

    private fun onScreenChanged(screen: Screen) {
        coroutineScope.launch {
            if (_routeUpdates.value != screen) {
                _routeUpdates.value = screen
            }
        }
    }
}
