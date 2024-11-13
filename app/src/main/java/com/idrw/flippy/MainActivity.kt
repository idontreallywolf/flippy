package com.idrw.flippy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.idrw.flippy.ui.theme.MyApplicationFlippyTheme
import com.idrw.flippy.utility.emojiToBitmap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationFlippyTheme {
                Navigation { MainScreen(it) }
            }
        }
    }
}
