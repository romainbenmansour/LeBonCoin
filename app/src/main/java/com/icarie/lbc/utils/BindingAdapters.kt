package com.icarie.lbc.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl)
}