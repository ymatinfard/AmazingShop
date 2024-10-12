package com.matin.amazingshop.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkImage(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)