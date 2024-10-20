package com.matin.amazingshop.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.matin.amazingshop.core.designsystem.R
import com.matin.amazingshop.core.model.Item

@Composable
fun WishlistIcon(
    item: Item,
    onFavoriteClick: (Item) -> Unit,
) {
    Image(
        painter = painterResource(id = if (item.isInWishlist) R.drawable.save_item_filled_ic else R.drawable.save_item_outline_ic),
        contentDescription = null,
        modifier = Modifier
            .size(24.dp)
            .clickable { onFavoriteClick(item) },
    )
}