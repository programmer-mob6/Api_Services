package com.example.apiservices.data.source.network.model.response

import com.google.common.truth.Truth
import org.junit.Test

class GetSupplierFilterResponseTest {
    @Test
    fun `verify default values`() {
        // Act
        val response = GetSupplierFilterResponse()

        // Assert
        Truth.assertThat(response.data).isEmpty()
        Truth.assertThat(response.status).isEqualTo("")
        Truth.assertThat(response.message).isEqualTo("")
    }

    @Test
    fun `verify object initialization`() {
        val expectedData = listOf(
            GetSupplierFilterResponse.Data(
                city = listOf(
                    GetSupplierFilterResponse.Data.City(
                        label = "city",
                        value = "city"
                    )
                ),
                isactive = listOf(
                    GetSupplierFilterResponse.Data.Isactive(
                        label = true,
                        value = true
                    )
                ),
                item = listOf(
                    GetSupplierFilterResponse.Data.Item(
                        label = "Item",
                        value = "Item"
                    )
                ),
                modifiedby = listOf(
                    GetSupplierFilterResponse.Data.Modifiedby(
                        label = "Modifiedby",
                        value = "Modifiedby"
                    )
                ),
                supplier = listOf(
                    GetSupplierFilterResponse.Data.Supplier(
                        label = "Supplier",
                        value = "Supplier"
                    )
                )
            )
        )

        // Act
        val response = GetSupplierFilterResponse(
            data = listOf(
                GetSupplierFilterResponse.Data(
                    city = listOf(
                        GetSupplierFilterResponse.Data.City(
                            label = "city",
                            value = "city"
                        )
                    ),
                    isactive = listOf(
                        GetSupplierFilterResponse.Data.Isactive(
                            label = true,
                            value = true
                        )
                    ),
                    item = listOf(
                        GetSupplierFilterResponse.Data.Item(
                            label = "Item",
                            value = "Item"
                        )
                    ),
                    modifiedby = listOf(
                        GetSupplierFilterResponse.Data.Modifiedby(
                            label = "Modifiedby",
                            value = "Modifiedby"
                        )
                    ),
                    supplier = listOf(
                        GetSupplierFilterResponse.Data.Supplier(
                            label = "Supplier",
                            value = "Supplier"
                        )
                    )
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