package com.example.apiservices.data.model.supplier

data class ChangeLogEntity(
    val action: String = "",
    val companyName: String = "",
    val field: String = "",
    val lastModified: String = "",
    val modifiedBy: String = "",
    val newValue: String = "",
    val oldValue: String = ""
)
