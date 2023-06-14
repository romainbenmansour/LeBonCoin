package com.icarie.base.ui.dialog

import com.icarie.base.ui.UIError

interface PopupManager {
    fun show(title: String, message: String)
    fun show(error: UIError)
    fun showDialog(dialogPayload: DialogPayload)
}
