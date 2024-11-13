package com.idrw.flippy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idrw.flippy.data.model.Deck

@Composable
fun DeckPreview(
    deck: Deck,
    onClick: () -> Unit,
    onClickOptions: () -> Unit
) {
    Card(onClick = { onClick() }, onLongClick = { onClickOptions() }) {
        DeckPreviewHeader(deck.title, onClickOptions = { onClickOptions() })
        DeckPreviewProgress(deck.cards, deck.cardsLearned)
    }
}

@Composable
fun DeckPreviewHeader(
    title: String,
    onClickOptions: () -> Unit
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DeckTitle(title)
        OptionsButton(onClick = { onClickOptions() })
    }
}

@Composable
fun DeckTitle(title: String) {
    Row (horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Icon(Icons.Default.Favorite, contentDescription = "")
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}
