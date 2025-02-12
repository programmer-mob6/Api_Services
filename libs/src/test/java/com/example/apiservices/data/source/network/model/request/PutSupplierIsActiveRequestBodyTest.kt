package com.example.apiservices.data.source.network.model.request

import com.google.common.truth.Truth
import com.google.gson.Gson
import org.junit.Test

class PutSupplierIsActiveRequestBodyTest {
    @Test
    fun `verify default values`() {
        // Act
        val request = PutSupplierIsActiveRequestBody()

        // Assert
        Truth.assertThat(request.ids).isEmpty()
        Truth.assertThat(request.isActive).isFalse()
    }

    @Test
    fun `verify object initialization`() {
        // Act
        val request = PutSupplierIsActiveRequestBody(
            ids = listOf("id"),
            isActive = true
        )

        // Assert
        Truth.assertThat(request.ids).isEqualTo(listOf("id"))
        Truth.assertThat(request.isActive).isTrue()
    }

    @Test
    fun testSerialization() {
        val ids = listOf("id1", "id2")
        val isActive = true
        val request = PutSupplierIsActiveRequestBody(ids, isActive)
        val gson = Gson()
        val jsonString = gson.toJson(request)
        val expectedJson = """{"ids":["id1","id2"],"isActive":true}"""
        Truth.assertThat(jsonString)
            .isEqualTo(expectedJson) // Memastikan JSON sesuai dengan yang diharapkan
    }

    @Test
    fun testDeserialization() {
        val jsonString = """{"ids":["id1","id2"],"isActive":true}"""
        val gson = Gson()
        val request = gson.fromJson(jsonString, PutSupplierIsActiveRequestBody::class.java)
        Truth.assertThat(request.ids).containsExactlyElementsIn(
            listOf(
                "id1",
                "id2"
            )
        ) // Memastikan ids sesuai dengan yang diinput dalam JSON
        Truth.assertThat(request.isActive).isTrue() // Memastikan isActive adalah true
    }
}