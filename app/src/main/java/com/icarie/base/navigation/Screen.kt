package com.icarie.base.navigation

import com.icarie.base.navigation.transitions.AdvancedTransitions
import com.icarie.base.navigation.transitions.ScreenTransitions

enum class Screen(
    val path: String,
    val transitions: ScreenTransitions
) {
    Splash(path = "splash", transitions = AdvancedTransitions.Main),
    None(path = "none", transitions = AdvancedTransitions.Main),
    Home(path = "home", transitions = AdvancedTransitions.Main);

    object Args {
        const val ARGUMENT_ID = "id"
    }

    fun createRoute(args: Map<String, Any> = emptyMap()): String {
        var result = path
        args.forEach {
            result = result.replace("{${it.key}}", it.value.toString())
        }
        return result
    }
}
