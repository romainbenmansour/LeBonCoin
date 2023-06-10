package com.icarie.base.navigation

import androidx.navigation.NavGraphBuilder
import com.icarie.base.navigation.transitions.ScreenTransitions

class GraphBuilder {

    private val graphs: MutableSet<Graph> = mutableSetOf()

    fun addGraph(graph: Graph): GraphBuilder =
        apply {
            graphs.add(graph)
        }

    fun build(navGraphBuilder: NavGraphBuilder) {
        graphs.forEach { graph ->
            graph.setup(graph, navGraphBuilder)
        }
    }
}

data class Graph(
    val name: String,
    val transitions: ScreenTransitions,
    val setup: Graph.(NavGraphBuilder) -> Unit,
)