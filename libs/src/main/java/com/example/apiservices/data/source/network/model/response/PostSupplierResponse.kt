package com.example.apiservices.data.source.network.model.response


import com.google.gson.annotations.SerializedName

data class PostSupplierResponse(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
)