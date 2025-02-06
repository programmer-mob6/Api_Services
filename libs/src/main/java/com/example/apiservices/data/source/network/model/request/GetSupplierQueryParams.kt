package com.example.apiservices.data.source.network.model.request

data class GetSupplierQueryParams(
    val page: Int? = null,
    val limit: Int? = null,
    val search: String? = null,
    val active: String? = null,
    val supplier: String? = null,
    val city: String? = null,
    val itemName: String? = null,
    val modifiedBy: String? = null,
    val lastModified: String? = null,
) {
    fun toQueryMap(): Map<String, String?> {
        return mapOf(
            "page" to page?.toString(),
            "limit" to limit?.toString(),
            "search" to search,
            "isactiveOptions" to "True",
            "supplierOptions" to "True",
            "cityOptions" to "True",
            "itemnameOptions" to "True",
            "modifiedbyOptions" to "True",
            "lastModified" to "True",

            "isactive" to active,
            "supplier" to supplier,
            "city" to city,
            "itemname" to itemName,
            "modifiedby" to modifiedBy,
            "lastmodified" to lastModified
        ).filterValues { it != null }
    }
}
