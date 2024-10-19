package com.matin.amazingshop.core.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matin.amazingshop.core.model.Item

@Composable
fun ItemSpec(item: Item, currency: String = "AED") {
    Text(text = item.brand, style = MaterialTheme.typography.labelSmall)
    Text(
        text = item.name,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.tertiary
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = "${item.price.setScale(2)} $currency",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.tertiary
    )
}