package com.example.apiservices.data.source.network.model.response

import com.google.common.truth.Truth
import org.junit.Test

class GetChangeLogFilterResponseTest {
    @Test
    fun `verify default values`() {
        // Act
        val response = GetChangeLogFilterResponse()

        // Assert
        Truth.assertThat(response.data).isEmpty()
        Truth.assertThat(response.status).isEqualTo("")
        Truth.assertThat(response.message).isEqualTo("")
    }

    @Test
    fun `verify object initialization`() {
        val expectedData = listOf(
            GetChangeLogFilterResponse.Data(
                action = listOf(
                    GetChangeLogFilterResponse.Data.Action(
                        label = "Action",
                        value = "Action"
                    )
                ),
                field = listOf(
                    GetChangeLogFilterResponse.Data.Field(
                        label = "field",
                        value = "field"
                    )
                ),
                modifiedby = listOf(
                    GetChangeLogFilterResponse.Data.Modifiedby(
                        label = "modifiedby",
                        value = "modifiedby"
                    )
                )
            )
        )

        // Act
        val response = GetChangeLogFilterResponse(
            data = listOf(
                GetChangeLogFilterResponse.Data(
                    action = listOf(
                        GetChangeLogFilterResponse.Data.Action(
                            label = "Action",
                            value = "Action"
                        )
                    ),
                    field = listOf(
                        GetChangeLogFilterResponse.Data.Field(
                            label = "field",
                            value = "field"
                        )
                    ),
                    modifiedby = listOf(
                        GetChangeLogFilterResponse.Data.Modifiedby(
                            label = "modifiedby",
                            value = "modifiedby"
                        )
                    )
                )
            ),
            message = "Operation completed",
            status = "success"
        )

        // Assert
        Truth.assertThat(response.data).isEqualTo(expectedData)
        Truth.assertThat(response.status).isEqualTo("success")
        Truth.assertThat(response.message).isEqualTo("Operation completed")
    }
}