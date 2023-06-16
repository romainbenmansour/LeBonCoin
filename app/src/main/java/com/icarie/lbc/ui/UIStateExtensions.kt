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