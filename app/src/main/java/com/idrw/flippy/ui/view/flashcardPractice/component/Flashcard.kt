package com.idrw.flippy.ui.view.flashcardPractice.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.LearnStatusIndicator
import com.idrw.flippy.ui.view.deck.LearnStatus

@Composable
fun Flashcard(
    flashcardData: Flashcard,
    onLongClick: () -> Unit,
    onChangeStatus: (learnStatus: LearnStatus) -> Unit
) {
    var showFront by remember { mutableStateOf(true) }

    Card (
        modifier = Modifier.fillMaxHeight(),
        onLongClick = onLongClick,
        onClick = {
            showFront = !showFront
        }
    ) {
        Row { Text(if (showFront) flashcardData.frontText else flashcardData.backText) }
        Row { LearnStatusIndicator(flashcardData.learnStatus, onChangeStatus = onChangeStatus) }
    }
}
