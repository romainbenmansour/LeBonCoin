package com.icarie.base.ui.dialog

import com.icarie.base.ui.dialog.ActionType

data class DialogAction(
    val type: ActionType,
    val title: String,
    val action: (() -> Unit) = {},
)
