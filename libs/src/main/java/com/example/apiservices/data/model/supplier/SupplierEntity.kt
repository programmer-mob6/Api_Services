package com.example.apiservices.data.model.supplier

data class SupplierEntity(
    val id: String = "",
    val companyName: String ="",
    val companyPhone: String ="",
    val companyAddress: String ="",
    val suppliedItemName: List<String> = emptyList(),
    val suppliedItemSku: List<String> = emptyList(),
    val suppliedItem: List<AddSupplierSuppliedItem> = emptyList(),
    val isActive: String = "",
    val city: String ="",
    val state: String ="",
    val country: String ="",
    val zipCode: String ="",
    val lastModified: String ="",
    val pic: String ="",
    val picPhone: String ="",
    val picEmail: String ="",
)

data class AddSupplierSuppliedItem(
    val itemName: String = "",
    val itemSku: List<String> = listOf(),
)