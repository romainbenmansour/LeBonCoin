package com.icarie.base.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icarie.base.navigation.common.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun start() {
        viewModelScope.launch {
            delay(2000)
            navigationManager.goToMainEntry()
        }
    }
}
