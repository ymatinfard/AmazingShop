package com.matin.amazingshop.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkItem(
    @SerializedName("id") var id: String? = null,
    @SerializedName("sku") var sku: String? = null,
    @SerializedName("image") var image: NetworkImage = NetworkImage(),
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("badges") var badges: ArrayList<String> = arrayListOf()
)