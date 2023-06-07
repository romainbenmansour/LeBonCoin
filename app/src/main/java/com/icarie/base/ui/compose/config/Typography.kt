package com.icarie.base.ui.compose.config

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.icarie.base.R

val fontsRoboto = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.Normal),
    Font(R.font.roboto_bold, weight = FontWeight.Bold)
)

val Typography.p2
    get() = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

val Typography.p3
    get() = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )

val Typography.p4
    get() = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    h2 = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    // p1
    body1 = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    // p3
    body2 = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    // p5
    subtitle1 = TextStyle(
        fontFamily = fontsRoboto,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
)
