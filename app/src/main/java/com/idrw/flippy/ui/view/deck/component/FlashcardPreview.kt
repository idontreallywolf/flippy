@file:OptIn(ExperimentalMaterial3Api::class)

package com.idrw.flippy.ui.view.deck.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idrw.flippy.R
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.LearnStatusIndicator
import com.idrw.flippy.ui.view.deck.Flashcard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FlashcardPreview(flashcardData: Flashcard, onLongClick: () -> Unit) {
    var status by remember { mutableStateOf(flashcardData.learnStatus) }

    Card (onLongClick = { onLongClick() }) {
        Row { Text(flashcardData.frontText) }
        Row { LearnStatusIndicator(status, onChangeStatus = { status = it }) }
    }
}

@Composable
fun FlashcardOptionMenu(
    isVisible: Boolean = false,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
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
                .clickable {scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) { onDismiss() }
                }}
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.trash),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = ""
            )
            Text("Hide bottom sheet")
        }
    }
}
