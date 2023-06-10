package com.icarie.base.navigation.transitions

import androidx.compose.animation.ExperimentalAnimationApi
import com.icarie.base.navigation.Screen

@OptIn(ExperimentalAnimationApi::class)
object AdvancedTransitions {

    internal val Main = BasicTransitions.SlideInHorizontal()
        .let { transition ->
            transition.copy(
                enterTransition = {
                    when (initialState.destination.route) {
                        // First screen
                        null -> BasicTransitions.None().enterTransition(this)
                        // Transition to first onBoarding
                        Screen.Splash.path -> BasicTransitions.Fade().enterTransition(this)
                        else -> transition.enterTransition(this)
                    }
                },
                exitTransition = {
                    BasicTransitions.Fade().exitTransition(this)
                }
            )
        }

    internal val SlideFromBottom = BasicTransitions.SlideInVertical()
}
