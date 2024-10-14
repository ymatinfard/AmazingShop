package com.matin.amazingshop.feature.catalog.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.matin.amazingshop.feature.catalog.CatalogScreen
import com.matin.amazingshop.feature.catalog.CatalogScreenViewModel

const val CATALOG_ROUTE = "catalog_route"

fun NavController.navigateToCatalog(navOptions: NavOptions) = navigate(CATALOG_ROUTE, navOptions)

fun NavGraphBuilder.catalogScreen(onWishlistClick: () -> Unit) {
    return composable(CATALOG_ROUTE) {
        val viewModel = hiltViewModel<CatalogScreenViewModel>()
        CatalogScreen(viewModel, onWishlistClick)
    }
}