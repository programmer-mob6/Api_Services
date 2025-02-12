package com.example.apiservices.data.source.network.model.response

import com.google.common.truth.Truth
import org.junit.Test

class GetSupplierResponseTest {
    @Test
    fun `verify default values`() {
        // Act
        val response = GetSupplierResponse()

        // Assert
        Truth.assertThat(response.data).isEmpty()
        Truth.assertThat(response.status).isEqualTo("")
        Truth.assertThat(response.message).isEqualTo("")
    }

    @Test
    fun `verify object initialization`() {
        val expectedData = listOf(
            GetSupplierResponse.Data(
                id = "id",
                city = "city",
                companyaddress = "companyaddress",
                companyname = "companyname",
                companyphone = "companyphone",
                country = "country",
                isactive = true,
                lastmodified = "lastmodified",
                modifiedby = "modifiedby",
                pic = "pic",
                picemail = "picemail",
                picphone = "picphone",
                state = "state",
                supplieditem = listOf(
                    GetSupplierResponse.Data.Supplieditem(
                        item = "item",
                        sku = listOf("sku")
                    )
                ),
                zipcode = "zipcode"
            )
        )

        // Act
        val response = GetSupplierResponse(
            data = listOf(
                GetSupplierResponse.Data(
                    id = "id",
                    city = "city",
                    companyaddress = "companyaddress",
                    companyname = "companyname",
                    companyphone = "companyphone",
                    country = "country",
                    isactive = true,
                    lastmodified = "lastmodified",
                    modifiedby = "modifiedby",
                    pic = "pic",
                    picemail = "picemail",
                    picphone = "picphone",
                    state = "state",
                    supplieditem = listOf(
                        GetSupplierResponse.Data.Supplieditem(
                            item = "item",
                            sku = listOf("sku")
                        )
                    ),
                    zipcode = "zipcode"
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