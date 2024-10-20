package com.matin.amazingshop.feature.catalog

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.matin.amazingshop.core.designsystem.R.drawable
import com.matin.amazingshop.core.designsystem.component.ItemBadge
import com.matin.amazingshop.core.designsystem.component.ItemSpec
import com.matin.amazingshop.core.designsystem.component.TopAppBar
import com.matin.amazingshop.core.model.Item

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun ItemDetailScreen(
    viewmodel: CatalogViewModel,
    onBackClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val selectedItem = viewmodel.selectedItem.collectAsStateWithLifecycle()

    selectedItem.value?.let { item ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            TopAppBar(
                title = stringResource(id = R.string.feature_catalog_detail),
                navigationIcon = Icons.Default.ArrowBack,
                navigationIconContentDescription = "back",
                actionIcon = painterResource(id = item.wishlistIcon()),
                actionIconContentDescription = "add to wishlist",
                onActionClick = {
                    viewmodel.onWishlistClick(item)
                    viewmodel.selectItem(item.copy(isInWishlist = item.isInWishlist.not()))
                },
                onNavigationClick = {
                    onBackClick()
                }
            )
            ItemDetailScreenContent(
                item = item, onAddToCartClick = {
                    viewmodel.onAddToCartClick(item)
                    viewmodel.selectItem(item.copy(isInCart = item.isInCart.not()))
                },
                sharedTransitionScope,
                animatedContentScope
            )
        }
    } ?: run {
        Text(text = "No item selected")
    }
}

fun Item.wishlistIcon() =
    if (isInWishlist) drawable.save_item_filled_ic else drawable.save_item_outline_ic

@OptIn(ExperimentalLayoutApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun ItemDetailScreenContent(
    item: Item,
    onAddToCartClick: (Item) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FlowRow(
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        item.badges.forEach {
                            ItemBadge(text = it, MaterialTheme.typography.labelLarge)
                        }
                    }
                }

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .padding(10.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = item.id),
                            animatedVisibilityScope = animatedContentScope,
                        ),
                    model = item.image.url,
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.ic_not_loaded),
                    contentDescription = null,
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ItemSpec(item)
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        onAddToCartClick(item)
                    },
                    Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(0),
                    enabled = item.isInCart.not()
                ) {
                    Text(
                        text = stringResource(R.string.feature_catalog_add_to_bag),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
