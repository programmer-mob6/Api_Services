package com.example.apiservices.data.model.supplier

import com.example.apiservices.base.OptionData

data class ChangeLogFilterEntity(
    val action: List<OptionData> = emptyList(),
    val field: List<OptionData> = emptyList(),
    val modifiedBy: List<OptionData> = emptyList()
)