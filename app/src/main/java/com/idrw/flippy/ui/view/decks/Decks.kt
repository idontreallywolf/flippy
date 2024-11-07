@file:OptIn(ExperimentalMaterial3Api::class)

package com.idrw.flippy.ui.view.decks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.idrw.flippy.LocalNavController
import com.idrw.flippy.R
import com.idrw.flippy.Routes
import com.idrw.flippy.ui.component.DeckPreview
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.ui.theme.CustomRed

import kotlinx.coroutines.launch

data class DeckData(
    val title: String,
    val cards: Int,
    val progress: Float
)

@Composable
fun Decks() {
    val items: List<DeckData> = listOf(
        DeckData("Mathematics", 10, 0.5f),
        DeckData("Physics", 10, 0.7f),
        DeckData("Cookies", 10, 0.23f),
        DeckData("Gymnastics", 10, 0.43f),
        DeckData("Biology", 10, 0.44f),
        DeckData("Psychology", 10, 0.87f),
        DeckData("Astronomy", 10, 0.98f),
        DeckData("Geography", 10, 1f),
        DeckData("History", 10, 0.63f),
    )

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val navController = LocalNavController.current

    PageContainer(title = "Decks") {
        LazyColumn (verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(items) {
                DeckPreview(
                    it.title, it.cards, it.progress,
                    onClick = { navController.navigate(Routes.Deck("123")) },
                    onClickOptions = { showBottomSheet = true }
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.padding(5.dp),
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                // Sheet content
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .clickable {scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
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

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(CustomRed)
                        .clickable {scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
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
                    Text("Hide bottom sheet")
                }
            }
        }
    }
}
