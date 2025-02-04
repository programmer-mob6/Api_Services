package com.example.apiservices.data.source.network.model.request


import com.google.gson.annotations.SerializedName

data class DeleteSupplierRequestBody(
    @SerializedName("ids")
    val ids: List<String> = listOf()
)