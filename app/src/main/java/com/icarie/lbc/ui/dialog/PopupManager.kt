package com.icarie.lbc.ui.dialog

import com.icarie.lbc.ui.UIError

interface PopupManager {
    fun show(title: String, message: String)
    fun show(error: UIError)
    fun showDialog(dialogPayload: DialogPayload)
}
