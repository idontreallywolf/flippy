package com.idrw.flippy.ui.view.newCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.idrw.flippy.LocalNavController
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.OutlinedInput
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.ui.view.newCard.component.Buttons
import com.idrw.flippy.ui.view.newCard.component.CardTabs

enum class CardTab { FRONT, BACK }

@Composable
fun NewCard(vm: NewCardViewModel, deckId: Int) {
    val navController = LocalNavController.current

    if (vm.flashcardCreated) {
        navController.navigateUp()
        return
    }

    PageContainer {
        Card (verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Text("Create new flashcard", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            CardTabs(onToggle = { vm.updateViewFront(it == CardTab.FRONT) })

            if (vm.viewFront) {
                OutlinedInput(
                    label = "front",
                    value = vm.frontText,
                    onValueChange = { vm.updateFrontText(it) },
                    singleLine = false,
                    isError = vm.frontText.length < 3
                )
            } else {
                OutlinedInput(
                    label = "back",
                    value = vm.backText,
                    onValueChange = { vm.updateBackText(it) },
                    singleLine = false,
                    isError = vm.backText.length < 3
                )
            }

            Buttons(
                onSave = { vm.createFlashcard() },
                onCancel = { navController.navigateUp() }
            )
        }
    }
}
