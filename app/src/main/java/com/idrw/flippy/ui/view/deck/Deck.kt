package com.idrw.flippy.ui.view.deck

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.idrw.flippy.LocalNavController
import com.idrw.flippy.Routes
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.ui.component.Circle
import com.idrw.flippy.ui.component.EmojiWithColor
import com.idrw.flippy.ui.component.LearnStatus
import com.idrw.flippy.ui.view.deck.component.FlashcardOptionMenu
import com.idrw.flippy.ui.view.deck.component.FlashcardPreview
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.ui.view.deck.component.DeckFilterMenu
import com.idrw.flippy.utility.colorByLearnStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Deck(vm: DeckViewModel, deckId: Int) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var viewOptionsMenu by remember { mutableStateOf(false) }
    var viewFilterMenu by remember { mutableStateOf(false) }
    var learnStatusFilter by remember { mutableStateOf<LearnStatus?>(LearnStatus.NOT_LEARNED) }
    val navController = LocalNavController.current

    val flashcards = vm.flashcards.collectAsState()
        .value.filter {
            (learnStatusFilter == null) ||
                it.learnStatus == learnStatusFilter
        }

    val currentDeck = vm.currentDeck.collectAsState().value

    var flashcardToModify by remember { mutableStateOf<Flashcard?>(null) }

    Box (modifier = Modifier.fillMaxSize()) {
        PageContainer(title = currentDeck.title, icon = {
            EmojiWithColor(
                emoji = currentDeck.emoji,
                emojiColor = currentDeck.emojiColor
            )
        }) {
            if (flashcards.isEmpty()) {
                Row (
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "There are no cards to display.",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp
                    )
                }
                return@PageContainer
            }

            LazyColumn (verticalArrangement = Arrangement.spacedBy(20.dp)) {
                item { Spacer(modifier = Modifier.size(10.dp)) }

                items(flashcards) { flashcardData ->
                    FlashcardPreview(
                        flashcardData,
                        onLongClick = {
                            flashcardToModify = flashcardData
                            viewOptionsMenu = true
                        },
                        onClick = { navController.navigate(
                            Routes.Flashcard(flashcardData.deckId, flashcardData.id, learnStatusFilter.toString())
                        ) },
                        onChangeStatus = { newStatus ->
                            vm.updateFlashcardStatus(flashcardData, newStatus)
                        }
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
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("New card")
                }

                Spacer(modifier = Modifier.size(10.dp))

                Button(
                    onClick = { viewFilterMenu = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Circle(colorByLearnStatus(learnStatusFilter))
                }
            }
        }
    }

    DeckFilterMenu(
        isVisible = viewFilterMenu,
        onSelectFilter = { learnStatusFilter = it },
        scope = scope,
        sheetState = sheetState,
        onDismiss = { viewFilterMenu = false }
    )

    FlashcardOptionMenu(
        isVisible = viewOptionsMenu,
        onClickDelete = {
            flashcardToModify?.let {
                vm.deleteFlashcard(flashcardToModify!!)
            }
            viewOptionsMenu = false
        },
        onClickEdit = {},
        scope = scope,
        sheetState = sheetState,
        onDismiss = { viewOptionsMenu = false }
    )
}
