package com.matin.amazingshop.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkBanner (
  @SerializedName("title") var title   : String? = null,
  @SerializedName("message") var message : String? = null
)