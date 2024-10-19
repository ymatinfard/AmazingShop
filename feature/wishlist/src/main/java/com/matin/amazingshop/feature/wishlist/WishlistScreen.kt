package com.matin.amazingshop.feature.wishlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.matin.amazingshop.core.designsystem.component.TopAppBar
import com.matin.amazingshop.core.model.Item

typealias RemoveClickListener = (Item) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(viewModel: WishlistViewModel, onCloseClick: () -> Unit) {
    val wishlistUiState = viewModel.wishlist.collectAsStateWithLifecycle()
    Column {
        TopAppBar(
            title = stringResource(
                R.string.feature_wishlist_wishlist,
                wishlistUiState.value.count()
            ),
            navigationIcon = null,
            navigationIconContentDescription = "",
            actionIcon = Icons.Default.Close,
            actionIconContentDescription = "Close page",
            onActionClick = {
                onCloseClick()
            }
        )
        WishlistScreenContent(wishlistUiState.value) { removedItem ->
            viewModel.removeFromWishlist(removedItem)
        }
    }
}

@Composable
fun WishlistScreenContent(
    wishlist: List<Item>,
    onRemoveClick: RemoveClickListener = {}
) {
    BoxWithConstraints {
        LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
            items(wishlist) {
                WishItem(
                    modifier = Modifier.height(maxHeight / 2),
                    item = it,
                    onRemoveClick = onRemoveClick
                )
            }
        }
    }
}

@Composable
fun WishItem(
    modifier: Modifier = Modifier,
    item: Item,
    onRemoveClick: RemoveClickListener = {}
) {
    ElevatedCard(onClick = { /*TODO*/ }, shape = RoundedCornerShape(0.dp)) {
        Column(modifier = modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(model = item.image.url, contentDescription = null, modifier.weight(.4f))
                Column(
                    modifier = Modifier
                        .weight(.6f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ItemSpec(item = item)
                    Remove(item = item, onClick = onRemoveClick)
                }
            }
        }
    }
}

@Composable
private fun Remove(item: Item, onClick: RemoveClickListener) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(interactionSource = interactionSource, indication = null) { onClick(item) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = stringResource(R.string.feature_wishlist_remove),
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
    }
}

@Composable
private fun ItemSpec(
    item: Item,
    currency: String = "AED"
) {
    Column {
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
}