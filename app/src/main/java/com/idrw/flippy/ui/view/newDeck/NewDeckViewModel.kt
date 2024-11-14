package com.idrw.flippy.ui.view.newDeck

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idrw.flippy.data.dao.DeckDAO
import com.idrw.flippy.data.database.FlippyAppDB
import com.idrw.flippy.data.model.Deck
import kotlinx.coroutines.launch

class NewDeckViewModel(context: Context): ViewModel() {
    private val db: FlippyAppDB = FlippyAppDB.getDatabase(context)
    private val dao: DeckDAO = db.deckDao()

    var deckCreated by mutableStateOf(false)
        private set

    fun createDeck(title: String, emoji: String, emojiColor: Int) {
        viewModelScope.launch {
            dao.insert(Deck(
                title = title,
                emoji = emoji,
                emojiColor = emojiColor
            ))
            deckCreated = true
        }
    }
}