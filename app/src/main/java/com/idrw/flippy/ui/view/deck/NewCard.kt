package com.idrw.flippy.ui.view.deck

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idrw.flippy.LocalNavController
import com.idrw.flippy.Routes
import com.idrw.flippy.ui.component.PageContainer

@Composable
fun NewCard(deckId: String) {
    val navController = LocalNavController.current

    PageContainer (modifier = Modifier.offset()) {
        Text("test")
        Button(onClick = { navController.navigateUp() }) { Text("Close") }
    }
}