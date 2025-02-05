package com.example.apiservices.data.source.network.model.request

data class GetChangeLogQueryParams(
    val page: Int? = null,
    val limit: Int? = null,
    val search: String? = null,
    val action: String? = null,
    val companyName: String? = null,
    val field: String? = null,
    val lastModified: String? = null,
    val modifiedBy: String? = null,
    val newValue: String? = null,
    val oldValue: String? = null
) {
    fun toQueryMap(): Map<String,String?> {
        return mapOf(
            "page" to page?.toString(),
            "limit" to limit?.toString(),
            "search" to search,
            "companyname" to companyName,
            "field" to field,
            "modifiedby" to modifiedBy,
            "lastmodified" to lastModified,
            "newvalue" to newValue,
            "oldvalue" to oldValue,
        ).filterValues { it != null }
    }
}
