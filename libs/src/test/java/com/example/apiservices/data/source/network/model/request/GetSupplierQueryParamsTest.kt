package com.example.apiservices.data.source.network.model.request

import com.google.common.truth.Truth
import org.junit.Test

class GetSupplierQueryParamsTest {
    @Test
    fun `verify default values`() {
        val params = GetSupplierQueryParams()
        val queryMap = params.toQueryMap()

        Truth.assertThat(queryMap).containsEntry("isactiveOptions", "True")
        Truth.assertThat(queryMap).containsEntry("supplierOptions", "True")
        Truth.assertThat(queryMap).containsEntry("cityOptions", "True")
        Truth.assertThat(queryMap).containsEntry("itemnameOptions", "True")
        Truth.assertThat(queryMap).containsEntry("modifiedbyOptions", "True")
        Truth.assertThat(queryMap).containsEntry("lastModified", "True")
        Truth.assertThat(queryMap).doesNotContainKey("page")
        Truth.assertThat(queryMap).doesNotContainKey("limit")
        Truth.assertThat(queryMap).doesNotContainKey("search")
        Truth.assertThat(queryMap).doesNotContainKey("isactive")
        Truth.assertThat(queryMap).doesNotContainKey("supplier")
        Truth.assertThat(queryMap).doesNotContainKey("city")
        Truth.assertThat(queryMap).doesNotContainKey("itemname")
        Truth.assertThat(queryMap).doesNotContainKey("modifiedby")
        Truth.assertThat(queryMap).doesNotContainKey("lastmodified")
    }

    @Test
    fun `verify object initialization`() {
        val params = GetSupplierQueryParams(
            page = 1,
            limit = 20,
            search = "testSearch",
            active = "true",
            supplier = "testSupplier",
            city = "testCity",
            itemName = "testItemName",
            modifiedBy = "testModifiedBy",
            lastModified = "2023-10-01"
        )
        val queryMap = params.toQueryMap()

        Truth.assertThat(queryMap).containsEntry("page", "1")
        Truth.assertThat(queryMap).containsEntry("limit", "20")
        Truth.assertThat(queryMap).containsEntry("search", "testSearch")
        Truth.assertThat(queryMap).containsEntry("isactiveOptions", "True")
        Truth.assertThat(queryMap).containsEntry("supplierOptions", "True")
        Truth.assertThat(queryMap).containsEntry("cityOptions", "True")
        Truth.assertThat(queryMap).containsEntry("itemnameOptions", "True")
        Truth.assertThat(queryMap).containsEntry("modifiedbyOptions", "True")
        Truth.assertThat(queryMap).containsEntry("lastModified", "True")
        Truth.assertThat(queryMap).containsEntry("isactive", "true")
        Truth.assertThat(queryMap).containsEntry("supplier", "testSupplier")
        Truth.assertThat(queryMap).containsEntry("city", "testCity")
        Truth.assertThat(queryMap).containsEntry("itemname", "testItemName")
        Truth.assertThat(queryMap).containsEntry("modifiedby", "testModifiedBy")
        Truth.assertThat(queryMap).containsEntry("lastmodified", "2023-10-01")
    }
}