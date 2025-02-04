package com.example.apiservices.data.source.network.model.request


import com.google.gson.annotations.SerializedName

data class PutSupplierIsActiveRequestBody(
    @SerializedName("ids")
    val ids: List<String> = listOf(),
    @SerializedName("isActive")
    val isActive: Boolean = false
)