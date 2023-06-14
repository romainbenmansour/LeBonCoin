package com.icarie.lbc.ui.dialog

data class DialogAction(
    val type: ActionType,
    val title: String,
    val action: (() -> Unit) = {},
)
