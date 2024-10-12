package com.matin.amazingshop.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkProductsData(
    @SerializedName("title") val title: String? = null,
    @SerializedName("currency") val currency: String? = null,
    @SerializedName("banner") val banner: NetworkBanner? = NetworkBanner(),
    @SerializedName("items") val items: ArrayList<NetworkItems>? = arrayListOf()
)