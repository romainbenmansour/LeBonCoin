package com.icarie.domain.app

import com.icarie.domain.common.Controller
import kotlinx.coroutines.flow.StateFlow

interface AppStateController : Controller {

    val state: StateFlow<AppState>
}
