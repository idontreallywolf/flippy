package com.idrw.flippy.ui.view.newCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewCardViewModel: ViewModel() {
    var viewFront by mutableStateOf(true)
        private set
    var frontText by mutableStateOf("")
        private set
    var backText by mutableStateOf("")
        private set

    fun updateViewFront(b: Boolean) { viewFront = b }
    fun updateFrontText(s: String) { frontText = s }
    fun updateBackText(s: String) { backText = s }
}