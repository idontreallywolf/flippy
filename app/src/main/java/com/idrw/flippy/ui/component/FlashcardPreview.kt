@file:OptIn(ExperimentalMaterial3Api::class)

package com.idrw.flippy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.idrw.flippy.R
import com.idrw.flippy.ui.theme.CustomGreen
import com.idrw.flippy.ui.theme.CustomOrange
import com.idrw.flippy.ui.theme.CustomRed
import com.idrw.flippy.ui.view.deck.Flashcard
import com.idrw.flippy.ui.view.deck.LearnStatus
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
fun LearnStatusIndicator(
    currentStatus: LearnStatus,
    onChangeStatus: (s: LearnStatus) -> Unit
) {
    var isChoosing by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier
            .clip(CircleShape)
            .clickable { isChoosing = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = CircleShape
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = 15.dp,
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isChoosing) {
            Circle(CustomGreen, onClick = {
                isChoosing = false
                onChangeStatus(LearnStatus.LEARNED)
            })
            Circle(CustomOrange, onClick = {
                isChoosing = false
                onChangeStatus(LearnStatus.UNSURE)
            })
            Circle(CustomRed, onClick = {
                isChoosing = false
                onChangeStatus(LearnStatus.NOT_LEARNED)
            })
            return@Row
        }

        Circle(colorByLearnStatus(currentStatus))
        Text(
            currentStatus.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.em,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )
        )
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
        modifier = Modifier.padding(20.dp),
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

@Composable
fun Circle(color: Color, size: Dp = 15.dp, onClick: (() -> Unit)? = null) {
    Box(modifier = Modifier
        .size(size)
        .clickable { onClick?.let { onClick() } }
        .clip(CircleShape)
        .background(color)
    )
}

fun colorByLearnStatus(learnStatus: LearnStatus): Color {
    return when(learnStatus) {
        LearnStatus.LEARNED -> CustomGreen
        LearnStatus.UNSURE -> CustomOrange
        LearnStatus.NOT_LEARNED -> CustomRed
    }
}