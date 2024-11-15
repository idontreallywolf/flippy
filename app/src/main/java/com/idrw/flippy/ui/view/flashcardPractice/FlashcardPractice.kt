package com.idrw.flippy.ui.view.flashcardPractice

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.ui.component.EmojiWithColor
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.ui.view.flashcardPractice.component.Flashcard

@Composable
fun FlashcardPractice(vm: FlashcardPracticeViewModel, pageIndex: Int) {
    val flashcards: List<Flashcard> = vm.flashcards.collectAsState().value

    val pagerState = rememberPagerState(
        pageCount = { flashcards.size },
        initialPage = pageIndex
    )

    val currentDeck = vm.currentDeck.collectAsState().value

    PageContainer (title = currentDeck.title, icon = {
        EmojiWithColor(
            emoji = currentDeck.emoji,
            emojiColor = currentDeck.emojiColor
        )
    }) {
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
