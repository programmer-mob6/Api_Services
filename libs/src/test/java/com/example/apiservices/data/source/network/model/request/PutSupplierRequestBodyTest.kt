package com.example.apiservices.data.source.network.model.request

import com.google.common.truth.Truth
import org.junit.Test

class PutSupplierRequestBodyTest {
    @Test
    fun `verify default values`() {
        // Act
        val request = PutSupplierRequestBody()

        // Assert
        Truth.assertThat(request.id).isEmpty()
        Truth.assertThat(request.city).isEmpty()
        Truth.assertThat(request.companyaddress).isEmpty()
        Truth.assertThat(request.companyname).isEmpty()
        Truth.assertThat(request.companyphone).isEmpty()
        Truth.assertThat(request.country).isEmpty()
        Truth.assertThat(request.pic).isEmpty()
        Truth.assertThat(request.picemail).isEmpty()
        Truth.assertThat(request.picphone).isEmpty()
        Truth.assertThat(request.state).isEmpty()
        Truth.assertThat(request.supplieditem).isEmpty()
        Truth.assertThat(request.zipcode).isEmpty()

    }

    @Test
    fun `verify object initialization`() {
        // arrange
        val id = "supplier123"
        val city = "New York"
        val companyaddress = "123 Main St"
        val companyname = "Acme Corp"
        val companyphone = "555-1234"
        val country = "USA"
        val pic = "John Doe"
        val picemail = "john.doe@example.com"
        val picphone = "555-5678"
        val state = "NY"
        val suppliedItem = PutSupplierRequestBody.Supplieditem("Widget", listOf("SKU123"))
        val zipcode = "10001"

        // Act
        val request = PutSupplierRequestBody(
            id,
            city,
            companyaddress,
            companyname,
            companyphone,
            country,
            pic,
            picemail,
            picphone,
            state,
            listOf(suppliedItem),
            zipcode
        )

        // assert
        Truth.assertThat(request.id).isEqualTo(id)
        Truth.assertThat(request.city).isEqualTo(city)
        Truth.assertThat(request.companyaddress).isEqualTo(companyaddress)
        Truth.assertThat(request.companyname).isEqualTo(companyname)
        Truth.assertThat(request.companyphone).isEqualTo(companyphone)
        Truth.assertThat(request.country).isEqualTo(country)
        Truth.assertThat(request.pic).isEqualTo(pic)
        Truth.assertThat(request.picemail).isEqualTo(picemail)
        Truth.assertThat(request.picphone).isEqualTo(picphone)
        Truth.assertThat(request.state).isEqualTo(state)
        Truth.assertThat(request.supplieditem).containsExactly(suppliedItem)
        Truth.assertThat(request.zipcode).isEqualTo(zipcode)
    }
}