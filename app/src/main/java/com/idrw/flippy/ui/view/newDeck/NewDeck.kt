package com.idrw.flippy.ui.view.newDeck

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.idrw.flippy.LocalNavController
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.OutlinedInput
import com.idrw.flippy.ui.component.PageContainer

@Composable
fun NewDeck(vm: NewDeckViewModel) {
    var deckTitle by remember { mutableStateOf("") }
    val navController = LocalNavController.current

    if (vm.deckCreated) {
        navController.navigateUp()
    }

    PageContainer {
        Card {
            OutlinedInput(
                "Deck Title",
                deckTitle,
                onValueChange = { deckTitle = it },
                isError = false,
                singleLine = true
            )

            Button(onClick = { vm.createDeck(deckTitle) }) {
                Text("Create Deck")
            }
        }
    }
}
