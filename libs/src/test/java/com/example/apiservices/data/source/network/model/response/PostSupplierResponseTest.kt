package com.example.apiservices.data.source.network.model.response

import com.google.common.truth.Truth
import org.junit.Test

class PostSupplierResponseTest {
    @Test
    fun `verify default values`() {
        // Act
        val response = PostSupplierResponse()

        // Assert
        Truth.assertThat(response.status).isEqualTo("")
        Truth.assertThat(response.message).isEqualTo("")
    }

    @Test
    fun `verify object initialization`() {
        // Act
        val response = PostSupplierResponse(status = "success", message = "Operation completed")

        // Assert
        Truth.assertThat(response.status).isEqualTo("success")
        Truth.assertThat(response.message).isEqualTo("Operation completed")
    }
}