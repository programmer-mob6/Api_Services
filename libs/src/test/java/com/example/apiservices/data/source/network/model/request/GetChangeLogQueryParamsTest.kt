package com.example.apiservices.data.source.network.model.request

import com.google.common.truth.Truth
import org.junit.Test

class GetChangeLogQueryParamsTest {
    @Test
    fun `verify default values`() {
        val params = GetChangeLogQueryParams()
        val queryMap = params.toQueryMap()

        Truth.assertThat(queryMap).containsEntry("actionOptions", "true")
        Truth.assertThat(queryMap).containsEntry("fieldOptions", "true")
        Truth.assertThat(queryMap).containsEntry("modifiedbyOptions", "true")
        Truth.assertThat(queryMap).doesNotContainKey("search")
        Truth.assertThat(queryMap).doesNotContainKey("action")
        Truth.assertThat(queryMap).doesNotContainKey("field")
        Truth.assertThat(queryMap).doesNotContainKey("modifiedby")
        Truth.assertThat(queryMap).doesNotContainKey("lastmodified")
    }

    @Test
    fun `verify object initialization`() {
        val params = GetChangeLogQueryParams(
            search = "testSearch",
            action = "testAction",
            field = "testField",
            modifiedBy = "testModifiedBy",
            date = "2023-10-01"
        )
        val queryMap = params.toQueryMap()

        Truth.assertThat(queryMap).containsEntry("search", "testSearch")
        Truth.assertThat(queryMap).containsEntry("actionOptions", "true")
        Truth.assertThat(queryMap).containsEntry("fieldOptions", "true")
        Truth.assertThat(queryMap).containsEntry("modifiedbyOptions", "true")
        Truth.assertThat(queryMap).containsEntry("action", "testAction")
        Truth.assertThat(queryMap).containsEntry("field", "testField")
        Truth.assertThat(queryMap).containsEntry("modifiedby", "testModifiedBy")
        Truth.assertThat(queryMap).containsEntry("lastmodified", "2023-10-01")
    }
}