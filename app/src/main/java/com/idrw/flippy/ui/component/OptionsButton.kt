package com.idrw.flippy.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idrw.flippy.R

@Composable
fun OptionsButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.padding(vertical = 2.dp),
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(R.drawable.menuvertical),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}