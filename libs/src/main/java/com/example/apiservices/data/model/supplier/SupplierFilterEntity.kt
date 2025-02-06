package com.example.apiservices.data.model.supplier

import com.example.apiservices.base.OptionData

data class SupplierFilterEntity(
    val active: List<OptionDataBoolean> = emptyList(),
    val supplier: List<OptionData> = emptyList(),
    val city: List<OptionData> = emptyList(),
    val itemName: List<OptionData> = emptyList(),
    val modifiedBy: List<OptionData> = emptyList()
)

data class OptionDataBoolean(
    val label: Boolean = false,
    val value: Boolean = false
)

