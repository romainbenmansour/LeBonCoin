package com.icarie.base.navigation.common

import com.icarie.base.navigation.Screen
import kotlinx.coroutines.flow.StateFlow

/**
 *  In charge of handling navigation from one view to another
 *  eg: Navigating to a login view
 */
interface NavigationManager {
    val screenUpdates: StateFlow<Screen>

    fun goBack()
    fun clearNavController()
    fun goToMainEntry()
}
