package com.icarie.base.ui.compose.states

import com.icarie.base.ui.compose.errors.UIError

sealed interface UIState<T> {
    class Loading<T> : UIState<T>
    class Failure<T>(val error: UIError, val onRetry: (() -> Unit)? = null) : UIState<T>
    class Success<T>(val data: T) : UIState<T>
    class Empty<T> : UIState<T>
    class None<T> : UIState<T>
}
