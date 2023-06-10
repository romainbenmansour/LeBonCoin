package com.icarie.domain.errors

sealed class AppError : Exception() {

    @Deprecated("Don't use this, use custom attributes instead")
    override val message: String = ""

    data class Api(val content: String) : AppError()
    object FeatureDisabled : AppError()
    object Offline : AppError()
    object Unknown : AppError()
}
