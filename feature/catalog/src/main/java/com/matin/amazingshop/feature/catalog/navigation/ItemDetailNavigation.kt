package com.matin.amazingshop.feature.catalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.matin.amazingshop.feature.catalog.CatalogViewModel
import com.matin.amazingshop.feature.catalog.ItemDetailScreen

const val ITEM_DETAIL_ROUTE = "item_detail_route"

fun NavController.navigateToItemDetail() = navigate(ITEM_DETAIL_ROUTE)

fun NavGraphBuilder.itemDetailScreen(sharedViewModel: CatalogViewModel, onBackClick: () -> Unit) {
    return composable(ITEM_DETAIL_ROUTE) {
        ItemDetailScreen(sharedViewModel, onBackClick)
    }
}