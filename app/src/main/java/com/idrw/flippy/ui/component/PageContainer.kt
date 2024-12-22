package com.idrw.flippy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PageContainer(
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: @Composable () -> Unit = {},
    dropDownOptions: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 15.dp, vertical = 8.dp)
    ) {
        title?.let { PageHeading(title, icon, dropDownOptions) }

        Column (
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            content()
        }
    }
}

@Composable
fun PageHeading(
    title: String,
    icon: @Composable () -> Unit,
    dropDownOptions: (@Composable () -> Unit)? = null
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        dropDownOptions?.let { dropDownOptions() }
    }
}