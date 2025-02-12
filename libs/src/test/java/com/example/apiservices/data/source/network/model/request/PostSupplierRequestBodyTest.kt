package com.example.apiservices.data.source.network.model.request

import com.google.common.truth.Truth
import org.junit.Test

class PostSupplierRequestBodyTest {
    @Test
    fun `verify default values`() {
        // Act
        val request = PostSupplierRequestBody()

        // Assert
        Truth.assertThat(request.companyname).isEmpty()
        Truth.assertThat(request.city).isEmpty()
        Truth.assertThat(request.country).isEmpty()
        Truth.assertThat(request.state).isEmpty()
        Truth.assertThat(request.zipcode).isEmpty()
        Truth.assertThat(request.pic).isEmpty()
        Truth.assertThat(request.picphone).isEmpty()
        Truth.assertThat(request.picemail).isEmpty()
        Truth.assertThat(request.supplieditem).isEmpty()
        Truth.assertThat(request.companyphone).isEmpty()
        Truth.assertThat(request.companyaddress).isEmpty()
    }

    @Test
    fun `verify object initialization`() {
        // Act
        val request = PostSupplierRequestBody(
            companyname = "companyname",
            city = "city",
            country = "country",
            state = "state",
            zipcode = "zipcode",
            pic = "pic",
            picphone = "picphone",
            picemail = "picemail",
            supplieditem = listOf(
                PostSupplierRequestBody.Supplieditem(
                    item = "item",
                    sku = listOf("sku")
                )
            ),
            companyphone = "companyphone",
            companyaddress = "companyaddress"
        )

        // Assert
        Truth.assertThat(request.companyname).isEqualTo("companyname")
        Truth.assertThat(request.city).isEqualTo("city")
        Truth.assertThat(request.country).isEqualTo("country")
        Truth.assertThat(request.state).isEqualTo("state")
        Truth.assertThat(request.zipcode).isEqualTo("zipcode")
        Truth.assertThat(request.pic).isEqualTo("pic")
        Truth.assertThat(request.picphone).isEqualTo("picphone")
        Truth.assertThat(request.picemail).isEqualTo("picemail")
        Truth.assertThat(request.supplieditem).isEqualTo(
            listOf(
                PostSupplierRequestBody.Supplieditem(
                    item = "item",
                    sku = listOf("sku")
                )
            )
        )
        Truth.assertThat(request.companyphone).isEqualTo("companyphone")
        Truth.assertThat(request.companyaddress).isEqualTo("companyaddress")
    }
}