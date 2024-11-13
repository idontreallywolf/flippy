package com.idrw.flippy.utility

import androidx.compose.ui.graphics.Color
import com.idrw.flippy.ui.component.LearnStatus
import com.idrw.flippy.ui.theme.CustomGreen
import com.idrw.flippy.ui.theme.CustomOrange
import com.idrw.flippy.ui.theme.CustomRed

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import androidx.palette.graphics.Palette

fun colorByLearnStatus(learnStatus: LearnStatus?): Color {
    return when(learnStatus) {
        LearnStatus.LEARNED -> CustomGreen
        LearnStatus.UNSURE -> CustomOrange
        LearnStatus.NOT_LEARNED -> CustomRed
        else -> Color.LightGray
    }
}

fun emojiToBitmap(emoji: String, size: Float = 100f): Bitmap {
    // Create a paint object with text properties
    val paint = Paint().apply {
        textSize = size
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        color = android.graphics.Color.BLACK
        typeface = Typeface.DEFAULT
    }

    // Measure text size
    val textBounds = android.graphics.Rect()
    paint.getTextBounds(emoji, 0, emoji.length, textBounds)

    // Create a bitmap with the size of the emoji
    val bitmap = Bitmap.createBitmap(textBounds.width(), textBounds.height(), Bitmap.Config.ARGB_8888)

    // Draw the emoji on a canvas
    val canvas = Canvas(bitmap)
    canvas.drawText(emoji, textBounds.exactCenterX(), -textBounds.top.toFloat(), paint)

    return bitmap
}

fun getEmojiColor(emoji: String): Int {
    return Palette.from(emojiToBitmap(emoji)).generate().lightVibrantSwatch?.rgb ?: 0
}

