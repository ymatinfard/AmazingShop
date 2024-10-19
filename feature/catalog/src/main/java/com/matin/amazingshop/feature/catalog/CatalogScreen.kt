package com.matin.amazingshop.feature.catalog

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.matin.amazingshop.core.designsystem.component.Banner
import com.matin.amazingshop.core.designsystem.component.ItemBadge
import com.matin.amazingshop.core.designsystem.component.ItemSpec
import com.matin.amazingshop.core.designsystem.component.LoadingWheel
import com.matin.amazingshop.core.designsystem.component.TopAppBar
import com.matin.amazingshop.core.designsystem.component.WishlistIcon
import com.matin.amazingshop.core.model.Item

typealias ClickListener = (Item) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    onWishlistClick: () -> Unit = {},
    onItemClick: () -> Unit = {}
) {
    val catalogState = viewModel.catalogUiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = stringResource(id = R.string.feature_catalog_new_in),
            actionIcon = Icons.Default.Favorite,
            onActionClick = { onWishlistClick() },
            navigationIcon = null,
            actionIconContentDescription = "wish list",
            navigationIconContentDescription = "",
        )

        CatalogScreenContent(catalogState.value, onItemClick = { item ->
            viewModel.selectItem(item)
            onItemClick()
        }, onWishlistClick = { item ->
            viewModel.onWishlistClick(item)
        })
    }
}

@Composable
fun CatalogScreenContent(
    catalog: CatalogUiState,
    onItemClick: ClickListener,
    onWishlistClick: ClickListener
) {
    when (catalog) {
        is CatalogUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingWheel()
            }
        }

        is CatalogUiState.Success -> {
            Catalog(catalog, onItemClick, onWishlistClick)
        }

        is CatalogUiState.Error -> {
            Log.e("Error", catalog.message)
        }
    }
}

@Composable
private fun Catalog(
    catalog: CatalogUiState.Success,
    onItemClick: ClickListener,
    onWishlistClick: ClickListener
) {
    var isBannerClosed by remember {
        mutableStateOf(false)
    }
    BoxWithConstraints(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
        val itemHeight = maxHeight / 2
        Column {
            if (catalog.data.banner.message.isNotEmpty() && isBannerClosed.not()) {
                Banner(banner = catalog.data.banner) { isBannerClosed = true }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(catalog.data.items) {
                    CatalogItem(it, height = itemHeight, onItemClick, onWishlistClick)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CatalogItem(
    item: Item,
    height: Dp,
    onItemClick: ClickListener,
    onWishlistClick: ClickListener,
) {
    Column(modifier = Modifier.height(height)) {
        ElevatedCard(
            shape = RoundedCornerShape(2.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            onClick = { onItemClick(item) }
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
                            ItemBadge(text = it, MaterialTheme.typography.labelSmall)
                        }
                    }
                    WishlistIcon(item, onWishlistClick)
                }

                AsyncImage(
                    modifier = Modifier
                        .height(160.dp)
                        .padding(10.dp),
                    model = item.image.url,
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    error = painterResource(id = com.matin.amazingshop.core.designsystem.R.drawable.fall_back_img)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ItemSpec(item)
    }
}