package com.idrw.flippy.ui.view.deck

import android.content.Context

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idrw.flippy.data.dao.DeckDAO
import com.idrw.flippy.data.dao.FlashcardDAO
import com.idrw.flippy.data.database.FlippyAppDB
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.data.model.Deck
import com.idrw.flippy.ui.component.LearnStatus
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeckViewModel(context: Context, deckId: Int): ViewModel() {
    var db = FlippyAppDB.getDatabase(context)
    var flashcardDao: FlashcardDAO = db.flashcardDao()
    var deckDao: DeckDAO = db.deckDao()

    val currentDeck = deckDao.findDeckById(deckId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Deck(1, title = "Loading...", cards = 0, progress = 0f)
    )

    var flashcards: StateFlow<List<Flashcard>> = flashcardDao.getAllByDeckId(deckId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun updateFlashcardStatus(flashcard: Flashcard, newStatus: LearnStatus) {
        viewModelScope.launch {
            flashcardDao.update(flashcard.copy(learnStatus = newStatus))
        }
    }

    fun deleteFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            flashcardDao.delete(flashcard)
            deckDao.decrementCountById(flashcard.deckId)
        }
    }

    fun filterFlashcards(learnStatus: LearnStatus): List<Flashcard> {
        return flashcards.value.filter { it.learnStatus == learnStatus }
    }
}