package com.idrw.flippy.ui.view.newCard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.idrw.flippy.ui.view.newCard.CardTab


@Composable
fun CardTabs(onToggle: (CardTab) -> Unit) {
    var selectedTab by remember { mutableStateOf(CardTab.FRONT) }

    val frontButtonColor =
        if (selectedTab != CardTab.FRONT)
            MaterialTheme.colorScheme.background
        else
            MaterialTheme.colorScheme.secondary

    val backButtonColor =
        if (selectedTab != CardTab.BACK)
            MaterialTheme.colorScheme.background
        else
            MaterialTheme.colorScheme.secondary

    Row (modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
    ) {
        Row (modifier = Modifier
            .weight(0.5f)
            .clickable {
                onToggle(CardTab.FRONT)
                selectedTab = CardTab.FRONT
            }
            .background(frontButtonColor)
            .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Front", fontWeight = FontWeight.SemiBold)
        }

        Row (modifier = Modifier
            .weight(0.5f)
            .clickable {
                onToggle(CardTab.BACK)
                selectedTab = CardTab.BACK
            }
            .background(backButtonColor)
            .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Back", fontWeight = FontWeight.SemiBold)
        }
    }
}