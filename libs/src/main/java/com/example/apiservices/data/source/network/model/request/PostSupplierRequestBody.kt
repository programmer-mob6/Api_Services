package com.example.apiservices.data.source.network.model.request


import com.google.gson.annotations.SerializedName

data class PostSupplierRequestBody(
    @SerializedName("companyname")
    val companyname: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("zipcode")
    val zipcode: String = "",
    @SerializedName("pic")
    val pic: String = "",
    @SerializedName("picphone")
    val picphone: String = "",
    @SerializedName("picemail")
    val picemail: String = "",
    @SerializedName("supplieditem")
    val supplieditem: List<Supplieditem> = listOf(),
    @SerializedName("companyphone")
    val companyphone: String = "",
    @SerializedName("companyaddress")
    val companyaddress: String = ""
) {
    data class Supplieditem(
        @SerializedName("item")
        val item: String = "",
        @SerializedName("sku")
        val sku: List<String> = listOf()
    )
}