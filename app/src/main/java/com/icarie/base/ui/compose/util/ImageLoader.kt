package com.icarie.base.ui.compose.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.Transformation
import com.icarie.base.R

@Composable
fun loaderImagePainter(
    data: String,
    @DrawableRes placeHolder: Int = R.drawable.ic_placeholder,
    @DrawableRes error: Int = R.drawable.ic_placeholder,
    transformations: List<Transformation> = emptyList()
): AsyncImagePainter {

    val context = LocalContext.current
    return rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .transformations(transformations)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .data(data)
            .placeholder(placeHolder)
            .error(error)
            .size(Size.ORIGINAL)
            .allowConversionToBitmap(true)
            .allowHardware(true)
            .crossfade(true)
            .build(),
    )
}
