package com.matin.amazingshop.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.matin.amazingshop.core.model.Item

@Composable
fun WishlistIcon(
    item: Item,
    onFavoriteClick: (Item) -> Unit,
) {
    Image(
        imageVector = if (item.isInWishlist) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = null,
        modifier = Modifier
            .clip(
                CircleShape,
            )
            .background(color = MaterialTheme.colorScheme.outlineVariant)
            .padding(3.dp)
            .clickable { onFavoriteClick(item) },
        colorFilter = ColorFilter.tint(if (item.isInWishlist) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface),
    )
}