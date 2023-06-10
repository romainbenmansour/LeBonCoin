@file:OptIn(ExperimentalAnimationApi::class)

package com.icarie.base.di

import androidx.compose.animation.ExperimentalAnimationApi
import com.icarie.base.navigation.Graph
import com.icarie.base.navigation.GraphBuilder
import com.icarie.base.navigation.NavigationManagerImpl
import com.icarie.base.navigation.common.MutableNavigationManager
import com.icarie.base.navigation.common.NavigationManager
import com.icarie.base.navigation.homeGraph
import com.icarie.base.navigation.transitions.BasicTransitions
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

const val GRAPH_FOR_YOU = "GRAPH_HOME"

@Module
@InstallIn(SingletonComponent::class)
class NavigationProviderAppModule {

    @Provides
    fun providesNavigationManager(navigationManager: NavigationManager): MutableNavigationManager =
        navigationManager as MutableNavigationManager

    @Provides
    fun providesGraphBuilder(): GraphBuilder = GraphBuilder()
        .addGraph(
            Graph(
                GRAPH_FOR_YOU,
                transitions = BasicTransitions.Fade(),
                setup = {
                    it.homeGraph(this)
                }
            )
        )

    @Module
    @InstallIn(SingletonComponent::class)
    interface Bindings {

        @Binds
        fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager
    }
}
