package com.matin.amazingshop.core.model

data class ProductsData(
    val title: String,
    val currency: String,
    val banner: Banner,
    val items: ArrayList<Items>
)