package com.icarie.lbc.ui.dialog

data class DialogPayload(
    val title: String,
    val message: String,
    val isCancelable: Boolean = true,
    val actions: List<DialogAction> = emptyList()
)
