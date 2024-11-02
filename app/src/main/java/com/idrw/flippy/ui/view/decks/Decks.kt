package com.idrw.flippy.ui.view.decks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.idrw.flippy.ui.component.DeckPreview
import com.idrw.flippy.ui.component.PageContainer

data class DeckData(
    val title: String,
    val cards: Int,
    val progress: Float
)

@Composable
fun Decks() {
    val items: List<DeckData> = listOf(
        DeckData("Mathematics", 10, 0.5f),
        DeckData("Physics", 10, 0.7f),
        DeckData("Cookies", 10, 0.23f),
        DeckData("Gymnastics", 10, 0.43f),
        DeckData("Biology", 10, 0.44f),
        DeckData("Psychology", 10, 0.87f),
        DeckData("Astronomy", 10, 0.98f),
        DeckData("Geography", 10, 1f),
        DeckData("History", 10, 0.63f),
    )
    PageContainer(title = "Decks") {
        LazyColumn (verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(items) {
                DeckPreview(it.title, it.cards, it.progress, onClick = {})
            }
        }
    }
}
