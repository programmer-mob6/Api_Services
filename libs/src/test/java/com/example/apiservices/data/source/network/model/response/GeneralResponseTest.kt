package com.example.apiservices.data.source.network.model.response

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Test

class GeneralResponseTest {
    @Test
    fun `verify default values`() {
        // Act
        val response = GeneralResponse()

        // Assert
        assertThat(response.status).isEqualTo("")
        assertThat(response.message).isEqualTo("")
    }

    @Test
    fun `verify object initialization`() {
        // Act
        val response = GeneralResponse(status = "success", message = "Operation completed")

        // Assert
        assertThat(response.status).isEqualTo("success")
        assertThat(response.message).isEqualTo("Operation completed")
    }

    @Test
    fun `verify JSON serialization`() {
        // Arrange
        val response = GeneralResponse(status = "error", message = "Something went wrong")
        val gson = Gson()

        // Act
        val json = gson.toJson(response)

        // Assert
        val expectedJson = """{"status":"error","message":"Something went wrong"}"""
        assertThat(json).isEqualTo(expectedJson)
    }

    @Test
    fun `verify JSON deserialization`() {
        // Arrange
        val json = """{"status":"success","message":"Data retrieved successfully"}"""
        val gson = Gson()

        // Act
        val response = gson.fromJson(json, GeneralResponse::class.java)

        // Assert
        assertThat(response.status).isEqualTo("success")
        assertThat(response.message).isEqualTo("Data retrieved successfully")
    }
}