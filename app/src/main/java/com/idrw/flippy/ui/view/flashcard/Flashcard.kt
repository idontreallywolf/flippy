package com.idrw.flippy.ui.view.flashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.LearnStatusIndicator
import com.idrw.flippy.ui.component.PageContainer
import com.idrw.flippy.ui.view.deck.LearnStatus

@Composable
fun Flashcard(deckId: Int, cardId: Int) {
    // Display 10 items
    val pagerState = rememberPagerState(pageCount = {
        10
    })

    Text("Flashcard Deck: $deckId, ID: $cardId")
    PageContainer {
        HorizontalPager(state = pagerState, pageSpacing = 10.dp) { page ->
            var showFront by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                onClick = { showFront = !showFront }
            ) {
                Text("viewing page $page")

                LearnStatusIndicator(
                    LearnStatus.LEARNED,
                    onChangeStatus = {}
                )
            }
        }
    }
}
