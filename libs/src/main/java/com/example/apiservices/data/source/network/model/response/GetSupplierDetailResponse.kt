package com.example.apiservices.data.source.network.model.response

import com.google.gson.annotations.SerializedName

data class GetSupplierDetailResponse(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
) {
    data class Data(
        @SerializedName("companyname")
        val companyname: String = "",
        @SerializedName("isactive")
        val isactive: Boolean = false,
        @SerializedName("city")
        val city: String = "",
        @SerializedName("country")
        val country: String = "",
        @SerializedName("state")
        val state: String = "",
        @SerializedName("zipcode")
        val zipcode: String = "",
        @SerializedName("lastmodified")
        val lastmodified: String = "",
        @SerializedName("pic")
        val pic: String = "",
        @SerializedName("picphone")
        val picphone: String = "",
        @SerializedName("picemail")
        val picemail: String = "",
        @SerializedName("modifiedby")
        val modifiedby: String = "",
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
}