package com.icarie.lbc.ui

import com.icarie.domain.common.Status
import com.icarie.domain.common.fold

fun <T> Status<T>.toUIState(onRetry: (() -> Unit)? = null): UIState<T> =
    fold(
        onFailure = {
            UIState.Failure(error = it.toGlobalUIError(), onRetry = onRetry)
        },
        onSuccess = {
            UIState.Success(data = it)
        }
    )

fun <T, U> UIState<T>.map(transform: (T) -> U): UIState<U> = when (this) {
    is UIState.Failure -> UIState.Failure(error = error, onRetry = onRetry)
    is UIState.Loading -> UIState.Loading()
    is UIState.Success -> UIState.Success(data = transform(data))
    is UIState.None -> UIState.None()
    is UIState.Empty -> UIState.Empty()
}

inline fun <T> UIState<T>.onSuccess(block: UIState.Success<T>.() -> Unit) = apply { (this as? UIState.Success<T>)?.let(block) }
inline fun <T> UIState<T>.onLoading(block: UIState.Loading<T>.() -> Unit) = apply { (this as? UIState.Loading<T>)?.let(block) }
inline fun <T> UIState<T>.onFailure(block: UIState.Failure<T>.() -> Unit) = apply { (this as? UIState.Failure<T>)?.let(block) }
inline fun <T> UIState<T>.onEmpty(block: UIState.Empty<T>.() -> Unit) = apply { (this as? UIState.Empty<T>)?.let(block) }

inline fun <T> UIState<T>.onNotSuccess(block: () -> Unit) = apply {
    if (this !is UIState.Success<T>) {
        block()
    }
}
