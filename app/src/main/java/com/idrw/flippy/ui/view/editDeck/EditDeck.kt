package com.idrw.flippy.ui.view.editDeck

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idrw.flippy.LocalNavController
import com.idrw.flippy.data.model.Deck
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.OutlinedInput
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.utility.getEmojiColor
import com.makeappssimple.abhimanyu.composeemojipicker.ComposeEmojiPickerBottomSheetUI
import com.makeappssimple.abhimanyu.composeemojipicker.ComposeEmojiPickerEmojiUI
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDeck(vm: EditDeckViewModel) {
    val currentDeck: Deck by vm.currentDeck.collectAsState()
    var deckTitle by remember { mutableStateOf(currentDeck.title) }

    val navController = LocalNavController.current
    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var isModalBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedEmoji by remember { mutableStateOf(currentDeck.emoji) }
    var searchText by remember { mutableStateOf("") }

    if (vm.deckEdited) {
        navController.navigateUp()
    }

    LaunchedEffect(currentDeck) {
        deckTitle = currentDeck.title
        selectedEmoji = currentDeck.emoji
    }

    PageContainer {
        Card {
            val emojiIntColor = getEmojiColor(selectedEmoji)
            val color = Color(emojiIntColor)

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row (modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color.copy(alpha = 0.3f))
                    .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ComposeEmojiPickerEmojiUI(
                        emojiCharacter = selectedEmoji,
                        onClick = {
                            isModalBottomSheetVisible = true
                        },
                        fontSize = 25.sp,
                    )
                }
                OutlinedInput(
                    "Deck Title",
                    deckTitle,
                    onValueChange = { deckTitle = it },
                    isError = false,
                    singleLine = true
                )
            }

            Button(onClick = { vm.updateDeck(vm.currentDeck.value.copy(
                title = deckTitle,
                emoji = selectedEmoji,
                emojiColor = emojiIntColor
            )) }) {
                Text("Update Deck")
            }
        }
    }

    if (isModalBottomSheetVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            shape = RectangleShape,
            tonalElevation = 0.dp,
            onDismissRequest = {
                isModalBottomSheetVisible = false
                searchText = ""
            },
            dragHandle = null,
            // windowInsets = WindowInsets(0),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                ComposeEmojiPickerBottomSheetUI(
                    onEmojiClick = { emoji ->
                        isModalBottomSheetVisible = false
                        selectedEmoji = emoji.character
                    },
                    onEmojiLongClick = { emoji ->
                        Toast.makeText(
                            context,
                            emoji.unicodeName.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            },
                            Toast.LENGTH_SHORT,
                        ).show()
                    },
                    searchText = searchText,
                    updateSearchText = { updatedSearchText ->
                        searchText = updatedSearchText
                    },
                )
            }
        }
    }
}