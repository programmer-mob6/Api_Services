package com.example.apiservices.data.source.network.model.request

import com.google.gson.annotations.SerializedName

data class PutSupplierRequestBody(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("companyaddress")
    val companyaddress: String = "",
    @SerializedName("companyname")
    val companyname: String = "",
    @SerializedName("companyphone")
    val companyphone: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("pic")
    val pic: String = "",
    @SerializedName("picemail")
    val picemail: String = "",
    @SerializedName("picphone")
    val picphone: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("supplieditem")
    val supplieditem: List<Supplieditem> = listOf(),
    @SerializedName("zipcode")
    val zipcode: String = ""
) {
    data class Supplieditem(
        @SerializedName("item")
        val item: String = "",
        @SerializedName("sku")
        val sku: List<String> = listOf()
    )
}