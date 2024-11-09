package com.idrw.flippy.ui.view.deck

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idrw.flippy.data.dao.FlashcardDAO
import com.idrw.flippy.data.database.FlippyAppDB
import com.idrw.flippy.data.model.Flashcard
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeckViewModel(context: Context, deckId: Int): ViewModel() {
    var db = FlippyAppDB.getDatabase(context)
    var dao: FlashcardDAO = db.flashcardDao()

    var flashcards: StateFlow<List<Flashcard>> = dao.getAllByDeckId(deckId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun updateFlashcardStatus(flashcard: Flashcard, newStatus: LearnStatus) {
        viewModelScope.launch {
            dao.update(flashcard.copy(learnStatus = newStatus))
        }
    }

    fun deleteFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            dao.delete(flashcard)
        }
    }

    fun filterFlashcards(learnStatus: LearnStatus): List<Flashcard> {
        return flashcards.value.filter { it.learnStatus == learnStatus }
    }
}