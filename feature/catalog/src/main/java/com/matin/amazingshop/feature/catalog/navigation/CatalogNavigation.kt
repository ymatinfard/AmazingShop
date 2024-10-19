package com.matin.amazingshop.feature.catalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matin.amazingshop.feature.catalog.CatalogScreen
import com.matin.amazingshop.feature.catalog.CatalogViewModel

const val CATALOG_ROUTE = "catalog_route"

fun NavGraphBuilder.catalogScreen(sharedViewModel: CatalogViewModel, onWishlistClick: () -> Unit, onItemClick: () -> Unit) {
    return composable(CATALOG_ROUTE) {
        CatalogScreen(viewModel = sharedViewModel, onWishlistClick = onWishlistClick, onItemClick = onItemClick)
    }
}