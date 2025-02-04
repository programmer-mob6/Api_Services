package com.example.apiservices.data.source.network.model.response


import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("message")
    val message: String = ""
)