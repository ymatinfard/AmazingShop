package com.matin.amazingshop.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ItemBadge(text: String, textStyle: TextStyle) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .background(color = MaterialTheme.colorScheme.outlineVariant)
            .padding(horizontal = 2.dp)
    ) {
        Text(text = text, style = textStyle)
    }
}