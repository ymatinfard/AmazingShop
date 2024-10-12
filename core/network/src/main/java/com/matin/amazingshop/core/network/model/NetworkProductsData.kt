package com.matin.amazingshop.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkProductsData(
    @SerializedName("title") var title: String? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("banner") var banner: NetworkBanner? = NetworkBanner(),
    @SerializedName("items") var items: ArrayList<NetworkItems> = arrayListOf()
)