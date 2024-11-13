package com.idrw.flippy.ui.view.newCard

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idrw.flippy.data.dao.DeckDAO
import com.idrw.flippy.data.dao.FlashcardDAO
import com.idrw.flippy.data.database.FlippyAppDB
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.ui.component.LearnStatus
import kotlinx.coroutines.launch

class NewCardViewModel(context: Context, private val deckId: Int): ViewModel() {
    private val db: FlippyAppDB = FlippyAppDB.getDatabase(context)
    private val flashcardDao: FlashcardDAO = db.flashcardDao()
    private val deckDao: DeckDAO = db.deckDao()

    var viewFront by mutableStateOf(true)
        private set
    var frontText by mutableStateOf("")
        private set
    var backText by mutableStateOf("")
        private set

    var flashcardCreated by mutableStateOf(false)

    fun updateViewFront(b: Boolean) { viewFront = b }
    fun updateFrontText(s: String) { frontText = s }
    fun updateBackText(s: String) { backText = s }

    fun createFlashcard() {
        viewModelScope.launch {
            deckDao.incrementCountById(deckId)

            flashcardDao.insert(Flashcard(
                deckId = deckId,
                frontText = frontText,
                backText = backText,
                learnStatus = LearnStatus.NOT_LEARNED
            ))

            flashcardCreated = true
        }
    }
}