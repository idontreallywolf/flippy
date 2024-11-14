package com.idrw.flippy.ui.view.decks.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DeleteDeckConfirmDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    if (!isVisible) return

    AlertDialog(
        icon = { Icon(icon, contentDescription = "Example Icon") },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) { Text("Confirm") }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text("Dismiss") }
        }
    )
}