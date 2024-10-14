package com.matin.amazingshop.feature.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.matin.amazingshop.core.model.Item

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun CatalogScreen(viewModel: CatalogScreenViewModel, onWishlistClick: () -> Unit = {}) {
    val catalogState = viewModel.catalogUiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar("NEW IN", Icons.Default.Favorite, onWishlistClick)
        CatalogScreenContent(catalogState.value) { favoriteItem ->
            if (favoriteItem.isInWishlist) {
                viewModel.removeFromWishList(favoriteItem)
            } else {
                viewModel.addToWishList(favoriteItem)
            }
        }
    }
}

@Composable
fun TopBar(title: String, icon: ImageVector, onAction: () -> Unit = {}) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(0),
    ) {
        Box(
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(modifier = Modifier.clickable { onAction() }, imageVector = icon, contentDescription = null)
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = title)
            }
        }
    }
}

@Composable
fun CatalogScreenContent(catalog: CatalogUiState, onFavoriteItemClick: (Item) -> Unit) {
    when (catalog) {
        is CatalogUiState.Loading -> Text(text = "Loading...")
        is CatalogUiState.Success -> {
            Catalog(catalog, onFavoriteItemClick)
        }

        is CatalogUiState.Error -> {
            Text(text = "Error: ${catalog.message}")
        }
    }
}

@Composable
private fun Catalog(
    catalog: CatalogUiState.Success,
    onFavoriteItemClick: (Item) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(catalog.data.items) {
                CatalogItem(it, maxHeight = maxHeight, onFavoriteItemClick)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CatalogItem(
    item: Item,
    maxHeight: Dp,
    onFavoriteItemClick: (Item) -> Unit
) {
    Column(modifier = Modifier.height(maxHeight / 2)) {
        ElevatedCard(
            shape = RoundedCornerShape(2.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            onClick = { /*TODO*/ }
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    FlowRow(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                    ) {
                        item.badges.forEach {
                            ItemBadge(text = it)
                        }
                    }
                    FavoriteIcon(item, onFavoriteItemClick)
                }

                AsyncImage(
                    modifier = Modifier
                        .height(160.dp)
                        .padding(10.dp),
                    model = "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg",
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.ic_not_loaded),
                    contentDescription = null,
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = item.brand, style = MaterialTheme.typography.labelSmall)
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${item.price.setScale(2)} AED",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun FavoriteIcon(
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
        colorFilter = ColorFilter.tint(if (item.isInWishlist) Color.Red else Color.White),
    )
}

@Composable
fun ItemBadge(text: String) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .background(color = MaterialTheme.colorScheme.outlineVariant)
            .padding(horizontal = 2.dp)
    ) {
        Text(text = text, fontSize = 10.sp)
    }
}