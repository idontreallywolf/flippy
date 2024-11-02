package com.idrw.flippy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idrw.flippy.R
import com.idrw.flippy.ui.theme.CustomGreen

@Composable
fun DeckPreview(
    title: String,
    cards: Int,
    progress: Float,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() }
            .padding(15.dp),

        verticalArrangement = Arrangement.SpaceBetween
    ) {
        DeckPreviewHeader(title, onClickOptions = { onClick() })
        DeckPreviewProgress(cards, progress)
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

@Composable
fun OptionsButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .padding(vertical = 2.dp),
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(R.drawable.menuvertical),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DeckPreviewProgress(
    cards: Int,
    progress: Float
) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
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