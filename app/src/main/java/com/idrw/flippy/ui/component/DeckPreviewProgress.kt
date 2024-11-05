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
    progress: Float
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text("4 / $cards cards learned")
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
            color = CustomGreen,
            trackColor = MaterialTheme.colorScheme.background,
            gapSize = 0.dp,
            drawStopIndicator = {}
        )
    }
}