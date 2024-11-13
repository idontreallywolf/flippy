package com.idrw.flippy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idrw.flippy.ui.theme.CustomGreen

@Composable
fun DeckPreviewProgress(
    cards: Int,
    cardsLearned: Int
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text("$cardsLearned / $cards cards learned")
        LinearProgressIndicator(
            progress = { calculateProgress(cards, cardsLearned) },
            modifier = Modifier.fillMaxWidth(),
            color = CustomGreen,
            trackColor = MaterialTheme.colorScheme.background,
            gapSize = 0.dp,
            drawStopIndicator = {}
        )
    }
}

private fun calculateProgress(cards: Int, cardLearned: Int): Float {
    if (cards > 0) {
        return cardLearned.toFloat() / cards
    }

    return 0f
}
