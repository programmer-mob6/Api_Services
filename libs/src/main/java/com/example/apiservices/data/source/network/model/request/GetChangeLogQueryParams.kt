package com.example.apiservices.data.source.network.model.request

data class GetChangeLogQueryParams(
    val search: String? = null,
    val action: String? = null,
    val field: String? = null,
    val modifiedBy: String? = null,
    val date: String? = null
) {
    fun toQueryMap(): Map<String, String?> {
        return mapOf(
            "search" to search,
            "actionOptions" to "true",
            "fieldOptions" to "true",
            "modifiedbyOptions" to "true",

            "action" to action,
            "field" to field,
            "modifiedby" to modifiedBy,
            "lastmodified" to date
        ).filterValues { it != null }
    }
}
