package com.example.apiservices.data.source.network.model.request

import com.google.common.truth.Truth
import org.junit.Test

class DeleteSupplierRequestBodyTest {
    @Test
    fun `verify default values`() {
        // Act
        val request = DeleteSupplierRequestBody()

        // Assert
        Truth.assertThat(request.ids).isEmpty()
    }

    @Test
    fun `verify object initialization`() {
        // Act
        val request = DeleteSupplierRequestBody(ids = listOf("id"))

        // Assert
        Truth.assertThat(request.ids).isEqualTo(listOf("id"))
    }
}