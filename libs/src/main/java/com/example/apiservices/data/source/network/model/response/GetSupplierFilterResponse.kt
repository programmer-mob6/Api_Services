package com.example.apiservices.data.source.network.model.response

import com.google.gson.annotations.SerializedName

data class GetSupplierFilterResponse(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
) {
    data class Data(
        @SerializedName("city")
        val city: List<City> = listOf(),
        @SerializedName("isactive")
        val isactive: List<Isactive> = listOf(),
        @SerializedName("item")
        val item: List<Item> = listOf(),
        @SerializedName("modifiedby")
        val modifiedby: List<Modifiedby> = listOf(),
        @SerializedName("supplier")
        val supplier: List<Supplier> = listOf()
    ) {
        data class City(
            @SerializedName("label")
            val label: String = "",
            @SerializedName("value")
            val value: String = ""
        )

        data class Isactive(
            @SerializedName("label")
            val label: Boolean = false,
            @SerializedName("value")
            val value: Boolean = false
        )

        data class Item(
            @SerializedName("label")
            val label: String = "",
            @SerializedName("value")
            val value: String = ""
        )

        data class Modifiedby(
            @SerializedName("label")
            val label: String = "",
            @SerializedName("value")
            val value: String = ""
        )

        data class Supplier(
            @SerializedName("label")
            val label: String = "",
            @SerializedName("value")
            val value: String = ""
        )
    }
}