package com.idrw.flippy.ui.view.deck

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idrw.flippy.Routes
import com.idrw.flippy.ui.component.FlashcardOptionMenu
import com.idrw.flippy.ui.component.FlashcardPreview
import com.idrw.flippy.ui.component.PageContainer

enum class LearnStatus {
    LEARNED,
    PART_LEARNED,
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

    LaunchedEffect(Unit) {
        vm.generateRandomFlashcards()
    }

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
