package com.example.apiservices.data.source.network.model.response

import com.google.gson.annotations.SerializedName

data class GetChangeLogResponse(
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
        @SerializedName("action")
        val action: String = "",
        @SerializedName("companyname")
        val companyname: String = "",
        @SerializedName("field")
        val `field`: String = "",
        @SerializedName("lastmodified")
        val lastmodified: String = "",
        @SerializedName("modifiedby")
        val modifiedby: String = "",
        @SerializedName("newvalue")
        val newvalue: String = "",
        @SerializedName("oldvalue")
        val oldvalue: String = ""
    )
}