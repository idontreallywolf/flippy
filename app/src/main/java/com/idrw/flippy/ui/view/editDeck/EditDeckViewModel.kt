package com.idrw.flippy.ui.view.editDeck

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idrw.flippy.data.dao.DeckDAO
import com.idrw.flippy.data.database.FlippyAppDB
import com.idrw.flippy.data.model.Deck
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EditDeckViewModel(context: Context, deckId: Int): ViewModel() {
    private val db: FlippyAppDB = FlippyAppDB.getDatabase(context)
    private val dao: DeckDAO = db.deckDao()

    var deckEdited by mutableStateOf(false)
        private set

    val currentDeck = dao.findDeckById(deckId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Deck(1, title = "Loading...", cards = 0, progress = 0f, emoji = "😁")
    )

    fun updateDeck(deck: Deck) {
        viewModelScope.launch {
            dao.update(deck)
            deckEdited = true
        }
    }
}
