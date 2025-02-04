package com.example.apiservices.data.source.network.model.response


import com.google.gson.annotations.SerializedName

data class GetSupplierResponse(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
) {
    data class Data(
        @SerializedName("_id")
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
        @SerializedName("isactive")
        val isactive: Boolean = false,
        @SerializedName("lastmodified")
        val lastmodified: String = "",
        @SerializedName("modifiedby")
        val modifiedby: String = "",
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
}