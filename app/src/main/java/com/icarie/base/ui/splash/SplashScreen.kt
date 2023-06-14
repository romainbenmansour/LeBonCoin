package com.icarie.base.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.icarie.base.R
import com.icarie.base.ui.compose.UIScreen
import com.icarie.base.ui.compose.config.secondarySea

@Composable
fun SplashScreen() = UIScreen {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(secondarySea)
    ) {
        Loader(
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}

@Composable
fun Loader(modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.init))
    LottieAnimation(
        composition = composition,
        modifier = modifier
    )
}