package com.icarie.base.ui

import androidx.annotation.StringRes
import com.icarie.base.R
import com.icarie.domain.errors.AppError

fun AppError.toGlobalUIError() = toUIError()
fun AppError.toPopupUIError() = toUIError()

fun AppError.toContentUIError() =
    when (this) {
        AppError.Offline -> UIError.ContentOffline
        else -> toUIError()
    }

private fun AppError.toUIError() =
    when (this) {
        AppError.Offline -> UIError.Offline
        is AppError.Api -> UIError.Unknown
        AppError.Unknown -> UIError.Unknown
        AppError.FeatureDisabled -> UIError.Unknown
    }

enum class UIError(
    @StringRes val title: Int,
    @StringRes val content: Int,
) {
    Offline(
        R.string.errormessage_title_nonetwork,
        R.string.errormessage_text_notconnectedactivatewifiorchecksettings,
    ),
    ContentOffline(
        R.string.errormessage_title_unabletoloadcontent,
        R.string.errormessage_text_notconnectedtotheinternet,
    ),
    Unknown(
        R.string.genericerrormessage_title_anerroroccurred,
        R.string.genericerrormessage_text_somethingwrongtryagainlater,
    ),
}