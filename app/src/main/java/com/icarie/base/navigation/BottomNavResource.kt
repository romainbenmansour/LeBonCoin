package com.icarie.base.navigation

import com.icarie.base.R
import com.icarie.base.di.GRAPH_FOR_YOU
import com.icarie.base.navigation.extensions.BottomNavDestination

data class BottomNavResource(
    val selected: Int,
    val unselected: Int,
)

enum class BottomNavItem(
    val title: Int,
    val icon: BottomNavResource,
    val screen: Screen,
    val graph: String
) : BottomNavDestination {
    Home(
        title = R.string.tabbar_title_home,
        icon = BottomNavResource(
            selected = R.drawable.ic_bottom_nav_home_selected,
            unselected = R.drawable.ic_bottom_nav_hom_unselected
        ),
        screen = Screen.Home,
        graph = GRAPH_FOR_YOU
    );

    override val path: String
        get() = screen.path
}
