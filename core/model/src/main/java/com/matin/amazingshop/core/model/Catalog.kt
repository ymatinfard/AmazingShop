package com.matin.amazingshop.core.model

data class Catalog(
    val title: String,
    val currency: String,
    val banner: Banner,
    val items: List<Item>
)