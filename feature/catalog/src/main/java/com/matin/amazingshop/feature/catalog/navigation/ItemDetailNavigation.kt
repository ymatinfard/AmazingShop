package com.matin.amazingshop.feature.catalog.navigation

import androidx.navigation.NavController

const val ITEM_DETAIL_ROUTE = "item_detail_route"

fun NavController.navigateToItemDetail() = navigate(ITEM_DETAIL_ROUTE)