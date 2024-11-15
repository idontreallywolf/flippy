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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idrw.flippy.R
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.LearnStatus
import com.idrw.flippy.ui.component.LearnStatusIndicator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FlashcardPreview(
    flashcardData: Flashcard,
    onLongClick: () -> Unit,
    onClick: () -> Unit,
    onChangeStatus: (learnStatus: LearnStatus) -> Unit
) {
    Card (onLongClick = onLongClick, onClick = onClick) {
        Row { Text(flashcardData.frontText) }
        Row { LearnStatusIndicator(
            currentStatus = flashcardData.learnStatus,
            onChangeStatus = onChangeStatus)
        }
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
                    if (!sheetState.isVisible) { onClickDelete() }
                }}
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.trash),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = ""
            )
            Text("Delete")
        }
    }
}
