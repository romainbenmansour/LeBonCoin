@file:OptIn(ExperimentalAnimationApi::class)

package com.icarie.base.di

import androidx.compose.animation.ExperimentalAnimationApi
import com.icarie.base.navigation.splash.splashGraph
import com.icarie.base.navigation.Graph
import com.icarie.base.navigation.GraphBuilder
import com.icarie.base.navigation.NavigationManagerImpl
import com.icarie.base.navigation.common.MutableNavigationManager
import com.icarie.base.navigation.common.NavigationManager
import com.icarie.base.navigation.main.homeGraph
import com.icarie.base.navigation.transitions.BasicTransitions
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val GRAPH_FOR_YOU = "GRAPH_HOME"
const val GRAPH_SPLASH = "GRAPH_SPLASH"

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
        ).addGraph(
            Graph(
                GRAPH_SPLASH,
                transitions = BasicTransitions.Fade(),
                setup = {
                    it.splashGraph(this)
                }
            )
        )

    @Module
    @InstallIn(SingletonComponent::class)
    interface Bindings {

        @Binds
        @Singleton
        fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager
    }
}
