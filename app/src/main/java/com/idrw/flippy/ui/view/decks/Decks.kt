@file:OptIn(ExperimentalMaterial3Api::class)

package com.idrw.flippy.ui.view.decks

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.idrw.flippy.LocalNavController
import com.idrw.flippy.R
import com.idrw.flippy.Routes
import com.idrw.flippy.data.model.Deck
import com.idrw.flippy.ui.component.DeckPreview
import com.idrw.flippy.ui.component.EmojiWithColor
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.ui.theme.CustomRed
import com.idrw.flippy.utility.getEmojiColor

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Decks(vm: DecksViewModel) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val navController = LocalNavController.current
    val decks = vm.decks.collectAsState()

    var deckToModify by remember { mutableStateOf<Deck?>(null) }

    Box (modifier = Modifier.fillMaxSize()) {
        PageContainer(title = "Decks") {
            /*
            Row {
                EmojiWithColor(emoji = "💖")
                EmojiWithColor(emoji = "🤣")
                EmojiWithColor(emoji = "🎉")
                EmojiWithColor(emoji = "👒")
            }
            */
            LazyColumn (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(decks.value) {
                    DeckPreview(
                        it,
                        onClick = { navController.navigate(Routes.Deck(it.id)) },
                        onClickOptions = {
                            deckToModify = it
                            showBottomSheet = true
                        }
                    )
                }
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.padding(5.dp),
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .background(CustomRed)
                            .clickable {scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    deckToModify?.let {
                                        vm.deleteDeck(deckToModify!!)
                                    }
                                    deckToModify = null
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
                        Text("Delete deck")
                    }
                }
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
                    onClick = { navController.navigate(Routes.NewDeck) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(Icons.Default.Add, tint = MaterialTheme.colorScheme.onSecondary, contentDescription = "")
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("New Deck")
                }
            }
        }
    }
}
