package com.icarie.base.navigation.common

import androidx.navigation.NavController
import com.icarie.base.navigation.Screen
import com.icarie.base.navigation.common.NavigationManager

/**
 * Should only be used in the main Activity as a first init
 * NavigationService should be more than enough everywhere else
 */
interface MutableNavigationManager : NavigationManager {

    fun assignNavController(navController: NavController)
}
