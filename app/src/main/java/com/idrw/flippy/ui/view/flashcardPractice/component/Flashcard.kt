package com.idrw.flippy.ui.view.flashcardPractice.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.idrw.flippy.data.model.Flashcard
import com.idrw.flippy.ui.component.Card
import com.idrw.flippy.ui.component.LearnStatus
import com.idrw.flippy.ui.component.LearnStatusIndicator

@Composable
fun Flashcard(
    flashcardData: Flashcard,
    onLongClick: () -> Unit,
    onChangeStatus: (learnStatus: LearnStatus) -> Unit
) {
    var showFront by remember { mutableStateOf(true) }

    val rotation by animateFloatAsState(
        targetValue = if (!showFront) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (showFront) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (!showFront) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateColor by animateColorAsState(
        targetValue = if (showFront) Color.Red else Color.Blue,
        animationSpec = tween(500)
    )

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card (
            Modifier.fillMaxHeight(0.8f).fillMaxWidth(0.8f).graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            },
            onClick = { showFront = !showFront },
            onLongClick = onLongClick
        )
        {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (!showFront) Arrangement.End else Arrangement.Start
            ) {
                Text(
                    if (showFront) flashcardData.frontText else flashcardData.backText,
                    modifier = Modifier.graphicsLayer {
                        alpha = if (!showFront) animateBack else animateFront
                        rotationY = rotation
                    }
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (!showFront) Arrangement.End else Arrangement.Start
            ) {
                LearnStatusIndicator(modifier = Modifier.graphicsLayer {
                    alpha = if (!showFront) animateBack else animateFront
                    rotationY = rotation
                }, flashcardData.learnStatus, onChangeStatus = onChangeStatus)
            }
        }
    }
}
