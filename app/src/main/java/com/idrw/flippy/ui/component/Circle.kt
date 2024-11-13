package com.idrw.flippy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Circle(color: Color, size: Dp = 15.dp, onClick: (() -> Unit)? = null) {
    Box(modifier = Modifier
        .size(size).run {
            if (onClick != null) {
                clickable { onClick() }
            } else this
        }
        .clip(CircleShape)
        .background(color)
    )
}
