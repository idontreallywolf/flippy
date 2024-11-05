package com.idrw.flippy.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Card(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    onLongClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .background(MaterialTheme.colorScheme.primary)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(10.dp)
            )
            .combinedClickable(
                onLongClick = { onLongClick?.let { onLongClick() } },
                onClick = { onClick?.let { onClick() } },
                enabled = (onClick != null || onLongClick != null)
            )
            .padding(15.dp),

        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        content()
    }
}