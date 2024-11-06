package com.idrw.flippy.ui.view.deck

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.idrw.flippy.LocalNavController
import com.idrw.flippy.Routes
import com.idrw.flippy.ui.component.FlashcardOptionMenu
import com.idrw.flippy.ui.component.FlashcardPreview
import com.idrw.flippy.ui.component.PageContainer

enum class LearnStatus {
    LEARNED,
    UNSURE,
    NOT_LEARNED
}

data class Flashcard(
    val frontText: String,
    val backText: String,
    val learnStatus: LearnStatus
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Deck(vm: DeckViewModel, deckId: String) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var viewOptionsMenu by remember { mutableStateOf(false) }
    val navController = LocalNavController.current

    LaunchedEffect(Unit) {
        vm.generateRandomFlashcards()
    }

    Box (modifier = Modifier.fillMaxSize()) {
        PageContainer(title = "Deck") {
            LazyColumn (verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(vm.flashcards) {
                    FlashcardPreview(
                        it,
                        onLongClick = { viewOptionsMenu = true }
                        //onClick = { navController.navigate(Routes.Deck("123")) },
                        //onClickOptions = { showBottomSheet = true }
                    )
                }
                item { Spacer(modifier = Modifier.size(100.dp)) }
            }
        }

        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .clickable(enabled = false, onClick = {})
        ) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 20.dp, shape = CircleShape, clip = true)
                .background(MaterialTheme.colorScheme.primary)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = CircleShape
                )
                .padding(horizontal = 20.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate(Routes.NewCard(deckId)) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(Icons.Default.Add, tint = MaterialTheme.colorScheme.onSecondary, contentDescription = "")
                    Text("New card")
                }
            }
        }
    }

    FlashcardOptionMenu(
        isVisible = viewOptionsMenu,
        onClickDelete = {},
        onClickEdit = {},
        scope = scope,
        sheetState = sheetState,
        onDismiss = { viewOptionsMenu = false }
    )
}
