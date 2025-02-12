package com.example.apiservices.data.model.supplier

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SupplierEntityTest {
    @Test
    fun `SupplierEntity with default values`() {
        // Act
        val data = SupplierEntity()

        // Assert
        assertThat(data.id).isEqualTo("")
        assertThat(data.companyName).isEqualTo("")
        assertThat(data.companyPhone).isEqualTo("")
        assertThat(data.companyAddress).isEqualTo("")

        assertThat(data.suppliedItemName).isEmpty()
        assertThat(data.suppliedItemSku).isEmpty()
        assertThat(data.suppliedItem).isEmpty()

        assertThat(data.isActive).isEqualTo("")
        assertThat(data.city).isEqualTo("")
        assertThat(data.state).isEqualTo("")
        assertThat(data.country).isEqualTo("")
        assertThat(data.zipCode).isEqualTo("")
        assertThat(data.lastModified).isEqualTo("")
        assertThat(data.pic).isEqualTo("")
        assertThat(data.picPhone).isEqualTo("")
        assertThat(data.picEmail).isEqualTo("")
    }

    @Test
    fun `SupplierEntity with custom values`() {
        // Act
        val data = SupplierEntity(
            id = "id",
            companyName = "companyName",
            companyPhone = "companyPhone",
            companyAddress = "companyAddress",
            suppliedItemName = listOf("item name 1"),
            suppliedItemSku = listOf("item sku 1"),
            suppliedItem = listOf(AddSupplierSuppliedItem("item 1", listOf("sku 1"))),
            isActive = "isActive",
            city = "city",
            state = "state",
            country = "country",
            zipCode = "zipCode",
            lastModified = "lastModified",
            pic = "pic",
            picPhone = "picPhone",
            picEmail = "picEmail"
        )

        // Assert
        assertThat(data.id).isEqualTo("id")
        assertThat(data.companyName).isEqualTo("companyName")
        assertThat(data.companyPhone).isEqualTo("companyPhone")
        assertThat(data.companyAddress).isEqualTo("companyAddress")

        assertThat(data.suppliedItemName).isEqualTo(listOf("item name 1"))
        assertThat(data.suppliedItemSku).isEqualTo(listOf("item sku 1"))
        assertThat(data.suppliedItem).isEqualTo(
            listOf(
                AddSupplierSuppliedItem(
                    "item 1",
                    listOf("sku 1")
                )
            )
        )

        assertThat(data.isActive).isEqualTo("isActive")
        assertThat(data.city).isEqualTo("city")
        assertThat(data.state).isEqualTo("state")
        assertThat(data.country).isEqualTo("country")
        assertThat(data.zipCode).isEqualTo("zipCode")
        assertThat(data.lastModified).isEqualTo("lastModified")
        assertThat(data.pic).isEqualTo("pic")
        assertThat(data.picPhone).isEqualTo("picPhone")
        assertThat(data.picEmail).isEqualTo("picEmail")
    }

    @Test
    fun `AddSupplierSuppliedItem with default values`() {
        // Act
        val data = AddSupplierSuppliedItem()

        // Assert
        assertThat(data.itemName).isEqualTo("")
        assertThat(data.itemSku).isEmpty()
    }

    @Test
    fun `AddSupplierSuppliedItem with custom values`() {
        // Act
        val data = AddSupplierSuppliedItem(
            itemName = "item 1",
            itemSku = listOf("sku 1", "sku 2")
        )

        // Assert
        assertThat(data.itemName).isEqualTo("item 1")
        assertThat(data.itemSku).isEqualTo(listOf("sku 1", "sku 2"))
    }
}