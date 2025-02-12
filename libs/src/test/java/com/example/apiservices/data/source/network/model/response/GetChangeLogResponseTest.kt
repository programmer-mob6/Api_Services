package com.example.apiservices.data.source.network.model.response

import com.google.common.truth.Truth
import org.junit.Test

class GetChangeLogResponseTest {
    @Test
    fun `verify default values`() {
        // Act
        val response = GetChangeLogResponse()

        // Assert
        Truth.assertThat(response.data).isEmpty()
        Truth.assertThat(response.status).isEqualTo("")
        Truth.assertThat(response.message).isEqualTo("")
    }

    @Test
    fun `verify object initialization`() {
        val expectedData = listOf(
            GetChangeLogResponse.Data(
                id = "id",
                action = "action",
                companyname = "companyname",
                field = "field",
                lastmodified = "lastmodified",
                modifiedby = "modifiedby",
                newvalue = "newvalue",
                oldvalue = "oldvalue"
            )
        )

        // Act
        val response = GetChangeLogResponse(
            data = listOf(
                GetChangeLogResponse.Data(
                    id = "id",
                    action = "action",
                    companyname = "companyname",
                    field = "field",
                    lastmodified = "lastmodified",
                    modifiedby = "modifiedby",
                    newvalue = "newvalue",
                    oldvalue = "oldvalue"
                )
            ),
            status = "success",
            message = "Operation completed"
        )

        // Assert
        Truth.assertThat(response.data).isEqualTo(expectedData)
        Truth.assertThat(response.status).isEqualTo("success")
        Truth.assertThat(response.message).isEqualTo("Operation completed")
    }
}