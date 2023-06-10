package com.icarie.domain.common

import com.icarie.domain.errors.AppError

sealed class Status<T> {
    data class Success<T>(val data: T) : Status<T>()
    data class Error<T>(val error: AppError = AppError.Unknown) : Status<T>()
}

inline fun <T, U> Status<T>.flatMap(transform: (T) -> Status<U>): Status<U> =
    fold(
        onFailure = {
            Status.Error(it)
        },
        onSuccess = transform
    )

inline fun <T, U> Status<T>.map(transform: (T) -> U): Status<U> =
    fold(
        onFailure = {
            Status.Error(it)
        },
        onSuccess = {
            Status.Success(transform(it))
        }
    )

inline fun <T> Status<T>.onSuccess(action: (T) -> Unit): Status<T> {
    if (this is Status.Success) action(data)
    return this
}

inline fun <T> Status<T>.onFailure(action: (AppError) -> Unit): Status<T> {
    if (this is Status.Error) action(error)
    return this
}

inline fun <T, O> Status<T>.fold(onFailure: (AppError) -> O, onSuccess: (T) -> O): O {
    return when (this) {
        is Status.Error -> onFailure(error)
        is Status.Success -> onSuccess(data)
    }
}

inline val <T> Status<T>.dataOrNull: T? get() = (this as? Status.Success<T>)?.data

fun <T> Status<T>.asResult(): Result<T> = fold(
    onFailure = {
        Result.failure(it)
    },
    onSuccess = {
        Result.success(it)
    }
)

fun <T> Result<T>.asStatus(toAppError: (Throwable) -> AppError): Status<T> =
    fold(
        onFailure = {
            Status.Error(toAppError(it))
        },
        onSuccess = {
            Status.Success(it)
        }
    )
