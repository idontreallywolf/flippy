package com.idrw.flippy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF283139),
    primary = Color(0xFF2C3843),
    secondary = Color(0xFF45A5FF),

    onBackground = Color(0xFFF9F9F9),
    onPrimary = Color(0xFFF9F9F9),
    onSecondary = Color(0xFFFFFFFF)
)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFFAFAFA),
    primary = Color(0xFFFFFFFF),
    secondary = Color(0xFF45A5FF),

    onBackground = Color(0xFF283139),
    onPrimary = Color(0xFF283139),
    onSecondary = Color(0xFFFFFFFF)
)

@Composable
fun MyApplicationFlippyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}