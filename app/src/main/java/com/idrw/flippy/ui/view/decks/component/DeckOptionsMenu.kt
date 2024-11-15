package com.idrw.flippy.ui.view.decks.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idrw.flippy.R
import com.idrw.flippy.ui.theme.CustomRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckOptionsMenu(
    isVisible: Boolean,
    scope: CoroutineScope,
    sheetState: SheetState,
    onClickDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!isVisible) return

    ModalBottomSheet(
        modifier = Modifier.padding(5.dp),
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(CustomRed)
                .clickable {scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onClickDelete()
                    }
                }}
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.trash),
                tint = Color.White,
                contentDescription = ""
            )
            Text("Delete deck")
        }
    }
}