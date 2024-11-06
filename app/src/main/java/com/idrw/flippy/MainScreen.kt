package com.idrw.flippy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(page: @Composable () -> Unit) {
    Scaffold (
        bottomBar = {

        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            page()
        }
    }
}