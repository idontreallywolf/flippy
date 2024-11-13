package com.idrw.flippy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.idrw.flippy.utility.getEmojiColor

@Composable
fun EmojiWithColor(modifier: Modifier = Modifier, emoji: String) {
    Row (modifier = Modifier
        .size(30.dp)
        .clip(CircleShape)
        .background(Color(getEmojiColor(emoji)).copy(alpha = 0.3f))
        .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(emoji)
    }
}
