package com.idrw.flippy.ui.view.flashcardPractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.LearnStatusIndicator
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.ui.view.deck.LearnStatus
import com.idrw.flippy.ui.view.flashcardPractice.component.Flashcard

@Composable
fun FlashcardPractice(vm: FlashcardPracticeViewModel) {
    val flashcards: List<Flashcard> = vm.flashcards.collectAsState().value

    val pagerState = rememberPagerState(pageCount = {
        flashcards.size
    })

    val currentDeck = vm.currentDeck.collectAsState().value

    PageContainer (title = currentDeck.title) {
        HorizontalPager(state = pagerState, pageSpacing = 10.dp) { page ->
            val currentFlashcard = flashcards[page]
            Flashcard(
                currentFlashcard,
                onLongClick = {},
                onChangeStatus = { vm.updateFlashcardStatus(currentFlashcard, it) }
            )
        }
    }
}
