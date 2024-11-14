package com.idrw.flippy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.idrw.flippy.ui.theme.CustomGreen
import com.idrw.flippy.ui.theme.CustomOrange
import com.idrw.flippy.ui.theme.CustomRed
import com.idrw.flippy.utility.colorByLearnStatus

enum class LearnStatus {
    LEARNED { override fun toString(): String = "Learned" },
    UNSURE { override fun toString(): String = "Unsure" },
    NOT_LEARNED { override fun toString(): String = "Not Learned" };

    abstract override fun toString(): String

    companion object {
        fun fromString(s: String): LearnStatus = when(s) {
            "Learned"     -> LEARNED
            "Unsure"      -> UNSURE
            "Not Learned" -> NOT_LEARNED

            else -> LEARNED
        }
    }
}

@Composable
fun LearnStatusIndicator(
    currentStatus: LearnStatus,
    onChangeStatus: (s: LearnStatus) -> Unit
) {
    var isChoosing by remember { mutableStateOf(false) }

    Row (
        modifier = Modifier
            .clip(CircleShape)
            .clickable { isChoosing = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = CircleShape
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = 15.dp,
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isChoosing) {
            Circle(CustomGreen, onClick = {
                isChoosing = false
                onChangeStatus(LearnStatus.LEARNED)
            })
            Circle(CustomOrange, onClick = {
                isChoosing = false
                onChangeStatus(LearnStatus.UNSURE)
            })
            Circle(CustomRed, onClick = {
                isChoosing = false
                onChangeStatus(LearnStatus.NOT_LEARNED)
            })
            return@Row
        }

        Circle(colorByLearnStatus(currentStatus))
        Text(
            currentStatus.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.em,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )
        )
    }
}