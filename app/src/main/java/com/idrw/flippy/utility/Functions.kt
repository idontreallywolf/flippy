package com.idrw.flippy.utility

import androidx.compose.ui.graphics.Color
import com.idrw.flippy.ui.theme.CustomGreen
import com.idrw.flippy.ui.theme.CustomOrange
import com.idrw.flippy.ui.theme.CustomRed
import com.idrw.flippy.ui.view.deck.LearnStatus

fun colorByLearnStatus(learnStatus: LearnStatus?): Color {
    return when(learnStatus) {
        LearnStatus.LEARNED -> CustomGreen
        LearnStatus.UNSURE -> CustomOrange
        LearnStatus.NOT_LEARNED -> CustomRed
        else -> Color.LightGray
    }
}