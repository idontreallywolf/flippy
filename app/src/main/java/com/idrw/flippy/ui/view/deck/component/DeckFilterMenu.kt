package com.idrw.flippy.ui.view.deck.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.idrw.flippy.ui.component.Circle
import com.idrw.flippy.ui.component.LearnStatus
import com.idrw.flippy.ui.theme.CustomGreen
import com.idrw.flippy.ui.theme.CustomOrange
import com.idrw.flippy.ui.theme.CustomRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckFilterMenu(
    isVisible: Boolean,
    onSelectFilter: (filter: LearnStatus?) -> Unit,
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    if (!isVisible) {
        return
    }

    ModalBottomSheet(
        modifier = Modifier.padding(5.dp),
        onDismissRequest = { scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            onDismiss()
        } },
        sheetState = sheetState
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ColoredFilterButton(Color.Gray, onClick = { onSelectFilter(null) })
            ColoredFilterButton(CustomGreen, onClick = { onSelectFilter(LearnStatus.LEARNED) })
            ColoredFilterButton(CustomOrange, onClick = { onSelectFilter(LearnStatus.UNSURE) })
            ColoredFilterButton(CustomRed, onClick = { onSelectFilter(LearnStatus.NOT_LEARNED) })
        }
    }
}

@Composable
private fun ColoredFilterButton(color: Color, onClick: () -> Unit) {
    Row(
        Modifier
            .clip(CircleShape)
            .background(color.copy(alpha = 0.3f))
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Circle(color, size = 15.dp)
    }
}