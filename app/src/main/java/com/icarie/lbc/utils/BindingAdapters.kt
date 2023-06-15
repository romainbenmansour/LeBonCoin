package com.icarie.lbc.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.icarie.lbc.ui.UIState

@BindingAdapter("uiState", "viewValue")
fun <T>updateVisibilityForUIState(
    view: View,
    viewValue: Int,
    uiState: UIState<T>) {
    (uiState as? UIState.Success)?.let {
        view.visibility = viewValue
    }
}