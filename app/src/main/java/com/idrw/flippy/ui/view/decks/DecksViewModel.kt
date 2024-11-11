package com.idrw.flippy.ui.view.decks

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idrw.flippy.data.dao.DeckDAO
import com.idrw.flippy.data.database.FlippyAppDB
import com.idrw.flippy.data.model.Deck
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DecksViewModel(context: Context): ViewModel() {
    private val db: FlippyAppDB = FlippyAppDB.getDatabase(context)
    private val dao: DeckDAO = db.deckDao()

    var decks: StateFlow<List<Deck>> = dao.getAllDecks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun createDeck(title: String) {
        viewModelScope.launch {
            dao.insert(Deck(title = title))
        }
    }

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch {
            dao.delete(deck)
        }
    }
}
