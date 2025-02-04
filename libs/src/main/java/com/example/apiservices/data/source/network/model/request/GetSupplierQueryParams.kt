package com.example.apiservices.data.source.network.model.request


data class GetSupplierQueryParams(
    val page: Int? = null,
    val limit: Int? = null,
    val search: String? = null,
    val active: String? = null,
    val supplier: String? = null,
    val city: String? = null,
    val itemName: String? = null,
    val itemSku: String? = null,
    val modifiedBy: String? = null,
    val lastModified: String? = null,
) {
    fun toQueryMap(): Map<String,String?> {
        return mapOf(
            "page" to page?.toString(),
            "limit" to limit?.toString(),
            "search" to search,
            "active" to active,
            "companyName" to supplier,
            "city" to city,
            "itemName" to itemName,
            "itemSku" to itemSku,
            "pic" to modifiedBy,
            "lastModified" to lastModified,
        ).filterValues { it != null }
    }
}
