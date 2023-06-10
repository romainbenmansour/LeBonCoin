package com.icarie.base.navigation.common

import com.icarie.base.navigation.Screen
import kotlinx.coroutines.flow.StateFlow

/**
 *  In charge of handling navigation from one fragment to another
 *  eg: Navigating to a login view fragment
 */
interface NavigationManager {
    val screenUpdates: StateFlow<Screen>

    fun goBack()
    fun clearNavController()
}
