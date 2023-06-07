package com.icarie.base.ui.dialog

import com.icarie.base.ui.dialog.DialogAction

data class DialogPayload(
    val title: String,
    val message: String,
    val isCancelable: Boolean = true,
    val actions: List<DialogAction> = emptyList()
)
