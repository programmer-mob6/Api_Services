package com.example.apiservices.data.source.network.model.response

import com.google.common.truth.Truth
import org.junit.Test

class GetSupplierDetailResponseTest {
    @Test
    fun `verify default values`() {
        // Act
        val response = GetSupplierDetailResponse()

        // Assert
        Truth.assertThat(response.data).isEqualTo(GetSupplierDetailResponse.Data())
        Truth.assertThat(response.status).isEqualTo("")
        Truth.assertThat(response.message).isEqualTo("")
    }

    @Test
    fun `verify object initialization`() {
        val expectedData = GetSupplierDetailResponse.Data(
            companyname = "companyname",
            isactive = true,
            city = "city",
            country = "country",
            state = "state",
            zipcode = "zipcode",
            lastmodified = "lastmodified",
            pic = "pic",
            picphone = "picphone",
            picemail = "picemail",
            modifiedby = "modifiedby",
            supplieditem = listOf(
                GetSupplierDetailResponse.Data.Supplieditem(
                    item = "supplieditem",
                    sku = listOf("suppliedSku")
                )
            ),
            companyphone = "companyphone",
            companyaddress = "companyaddress"
        )

        // Act
        val response = GetSupplierDetailResponse(
            data = GetSupplierDetailResponse.Data(
                companyname = "companyname",
                isactive = true,
                city = "city",
                country = "country",
                state = "state",
                zipcode = "zipcode",
                lastmodified = "lastmodified",
                pic = "pic",
                picphone = "picphone",
                picemail = "picemail",
                modifiedby = "modifiedby",
                supplieditem = listOf(
                    GetSupplierDetailResponse.Data.Supplieditem(
                        item = "supplieditem",
                        sku = listOf("suppliedSku")
                    )
                ),
                companyphone = "companyphone",
                companyaddress = "companyaddress"
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