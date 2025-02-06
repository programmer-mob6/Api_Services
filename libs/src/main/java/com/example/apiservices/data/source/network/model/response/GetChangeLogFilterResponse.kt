package com.example.apiservices.data.source.network.model.response

import com.google.gson.annotations.SerializedName

data class GetChangeLogFilterResponse(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
) {
    data class Data(
        @SerializedName("action")
        val action: List<Action> = listOf(),
        @SerializedName("field")
        val `field`: List<Field> = listOf(),
        @SerializedName("modifiedby")
        val modifiedby: List<Modifiedby> = listOf()
    ) {
        data class Action(
            @SerializedName("label")
            val label: String = "",
            @SerializedName("value")
            val value: String = ""
        )

        data class Field(
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
    }
}