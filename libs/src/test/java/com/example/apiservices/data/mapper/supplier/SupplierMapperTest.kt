package com.example.apiservices.data.mapper.supplier

import com.example.apiservices.base.OptionData
import com.example.apiservices.data.model.supplier.AddSupplierSuppliedItem
import com.example.apiservices.data.model.supplier.ChangeLogFilterEntity
import com.example.apiservices.data.model.supplier.OptionDataBoolean
import com.example.apiservices.data.model.supplier.SupplierEntity
import com.example.apiservices.data.model.supplier.SupplierFilterEntity
import com.example.apiservices.data.source.network.model.response.GetChangeLogFilterResponse
import com.example.apiservices.data.source.network.model.response.GetChangeLogResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierDetailResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierFilterResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SupplierMapperTest {
    @Test
    fun `mapSuppliers with empty list returns empty list`() {
        // Arrange
        val input = emptyList<GetSupplierResponse.Data>()

        // Act
        val result = SupplierMapper().mapSuppliers(input)

        // Assert
        assertThat(result).isEmpty()
    }

    @Test
    fun `mapSuppliers maps multiple data correctly`() {
        // Arrange
        val input = listOf(
            GetSupplierResponse.Data(
                id = "1",
                companyname = "TechCorp",
                supplieditem = listOf(
                    GetSupplierResponse.Data.Supplieditem(sku = listOf("SKU123"))
                ),
                isactive = true,
                city = "Jakarta",
                country = "Indonesia",
                lastmodified = "2024-02-06",
                pic = "John Doe"
            ),
            GetSupplierResponse.Data(
                id = "2",
                companyname = "GadgetWorld",
                supplieditem = listOf(
                    GetSupplierResponse.Data.Supplieditem(sku = listOf("SKU789", "SKU456"))
                ),
                isactive = false,
                city = "Bandung",
                country = "Indonesia",
                lastmodified = "2024-02-07",
                pic = "Jane Doe"
            )
        )

        // Act
        val result = SupplierMapper().mapSuppliers(input)

        // Assert
        assertThat(result.size).isEqualTo(2)

        with(result[0]) {
            assertThat(id).isEqualTo("1")
            assertThat(companyName).isEqualTo("TechCorp")
            assertThat(suppliedItemSku).isEqualTo(listOf("SKU123"))
            assertThat(isActive).isEqualTo("Active")
            assertThat(city).isEqualTo("Jakarta")
            assertThat(country).isEqualTo("Indonesia")
            assertThat(lastModified).isEqualTo("2024-02-06")
            assertThat(pic).isEqualTo("John Doe")
        }

        with(result[1]) {
            assertThat(id).isEqualTo("2")
            assertThat(companyName).isEqualTo("GadgetWorld")
            assertThat(suppliedItemSku).isEqualTo(listOf("SKU789", "SKU456"))
            assertThat(isActive).isEqualTo("Inactive")
            assertThat(city).isEqualTo("Bandung")
            assertThat(country).isEqualTo("Indonesia")
            assertThat(lastModified).isEqualTo("2024-02-07")
            assertThat(pic).isEqualTo("Jane Doe")
        }
    }

    @Test
    fun `mapSupplier maps empty data correctly`() {
        // Arrange
        val input = GetSupplierDetailResponse.Data()

        // Act
        val result = SupplierMapper().mapSupplier(input)

        // Assert
        assertThat(result).isEqualTo(SupplierEntity(isActive = "Inactive"))
    }

    @Test
    fun `mapSupplier maps data correctly`() {
        // Arrange
        val input = GetSupplierDetailResponse.Data(
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
                    "item",
                    listOf("sku1", "sku2")
                )
            ),
            companyphone = "companyphone",
            companyaddress = "companyaddress"

        )

        // Act
        val result = SupplierMapper().mapSupplier(input)
        val expectedResult = SupplierEntity(
            companyName = "companyname",
            companyPhone = "companyphone",
            companyAddress = "companyaddress",
            suppliedItem = listOf(
                AddSupplierSuppliedItem(
                    itemName = "item",
                    itemSku = listOf("sku1", "sku2")
                )
            ),
            isActive = "Active",
            city = "city",
            state = "state",
            country = "country",
            zipCode = "zipcode",
            lastModified = "lastmodified",
            pic = "pic",
            picPhone = "picphone",
            picEmail = "picemail"
        )

        // Assert
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `mapSupplier maps data correctly when isactive false`() {
        // Arrange
        val input = GetSupplierDetailResponse.Data(
            companyname = "companyname",
            isactive = false,
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
                    "item",
                    listOf("sku1", "sku2")
                )
            ),
            companyphone = "companyphone",
            companyaddress = "companyaddress"

        )

        // Act
        val result = SupplierMapper().mapSupplier(input)
        val expectedResult = SupplierEntity(
            companyName = "companyname",
            companyPhone = "companyphone",
            companyAddress = "companyaddress",
            suppliedItem = listOf(
                AddSupplierSuppliedItem(
                    itemName = "item",
                    itemSku = listOf("sku1", "sku2")
                )
            ),
            isActive = "Inactive",
            city = "city",
            state = "state",
            country = "country",
            zipCode = "zipcode",
            lastModified = "lastmodified",
            pic = "pic",
            picPhone = "picphone",
            picEmail = "picemail"
        )

        // Assert
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `mapChangeLog with empty list returns empty list`() {
        // Arrange
        val input = emptyList<GetChangeLogResponse.Data>()

        // Act
        val result = SupplierMapper().mapChangeLog(input)

        // Assert
        assertThat(result).isEmpty()
    }

    @Test
    fun `mapChangeLog maps multiple data correctly`() {
        // Arrange
        val input = listOf(
            GetChangeLogResponse.Data(
                id = "id",
                action = "action",
                companyname = "companyname",
                field = "field",
                lastmodified = "lastmodified",
                modifiedby = "modifiedby",
                newvalue = "newvalue",
                oldvalue = "oldvalue"
            ),
            GetChangeLogResponse.Data(
                id = "id2",
                action = "action2",
                companyname = "companyname2",
                field = "field2",
                lastmodified = "lastmodified2",
                modifiedby = "modifiedby2",
                newvalue = "newvalue2",
                oldvalue = "oldvalue2"
            )
        )

        // Act
        val result = SupplierMapper().mapChangeLog(input)

        // Assert
        assertThat(result.size).isEqualTo(2)

        with(result[0]) {
            assertThat(action).isEqualTo("action")
            assertThat(companyName).isEqualTo("companyname")
            assertThat(field).isEqualTo("field")
            assertThat(lastModified).isEqualTo("lastmodified")
            assertThat(modifiedBy).isEqualTo("modifiedby")
            assertThat(newValue).isEqualTo("newvalue")
            assertThat(oldValue).isEqualTo("oldvalue")
        }
        with(result[1]) {
            assertThat(action).isEqualTo("action2")
            assertThat(companyName).isEqualTo("companyname2")
            assertThat(field).isEqualTo("field2")
            assertThat(lastModified).isEqualTo("lastmodified2")
            assertThat(modifiedBy).isEqualTo("modifiedby2")
            assertThat(newValue).isEqualTo("newvalue2")
            assertThat(oldValue).isEqualTo("oldvalue2")
        }
    }

    @Test
    fun `mapChangeLogFilter with empty list returns data correctly`() {
        // Arrange
        val input = emptyList<GetChangeLogFilterResponse.Data>()

        // Act
        val result = SupplierMapper().mapChangeLogFilter(input)

        // Assert
        assertThat(result).isEqualTo(ChangeLogFilterEntity())
    }

    @Test
    fun `mapChangeLogFilter maps multiple data correctly`() {
        // Arrange
        val input = listOf(
            GetChangeLogFilterResponse.Data(
                action = listOf(
                    GetChangeLogFilterResponse.Data.Action("action1", "action1"),
                    GetChangeLogFilterResponse.Data.Action("action2", "action2")
                ),
                field = listOf(
                    GetChangeLogFilterResponse.Data.Field("field1", "field1"),
                    GetChangeLogFilterResponse.Data.Field("field2", "field2")
                ),
                modifiedby = listOf(
                    GetChangeLogFilterResponse.Data.Modifiedby("modifiedby1", "modifiedby1"),
                    GetChangeLogFilterResponse.Data.Modifiedby("modifiedby2", "modifiedby2")
                )
            )
        )

        // Act
        val result = SupplierMapper().mapChangeLogFilter(input)
        val expectedResult = ChangeLogFilterEntity(
            action = listOf(
                OptionData("action1", "action1"),
                OptionData("action2", "action2")
            ),
            field = listOf(
                OptionData("field1", "field1"),
                OptionData("field2", "field2")
            ),
            modifiedBy = listOf(
                OptionData("modifiedby1", "modifiedby1"),
                OptionData("modifiedby2", "modifiedby2")
            )
        )

        // Assert
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `mapSupplierFilter with empty list returns data correctly`() {
        // Arrange
        val input = emptyList<GetSupplierFilterResponse.Data>()

        // Act
        val result = SupplierMapper().mapSupplierFilter(input)

        // Assert
        assertThat(result).isEqualTo(SupplierFilterEntity())
    }

    @Test
    fun `mapSupplierFilter maps multiple data correctly`() {
        // Arrange
        val input = listOf(
            GetSupplierFilterResponse.Data(
                city = listOf(
                    GetSupplierFilterResponse.Data.City("city 1", "city 1"),
                    GetSupplierFilterResponse.Data.City("city 2", "city 2")
                ),
                isactive = listOf(
                    GetSupplierFilterResponse.Data.Isactive(label = true, value = true),
                    GetSupplierFilterResponse.Data.Isactive(label = false, value = false)
                ),
                item = listOf(
                    GetSupplierFilterResponse.Data.Item("Item 1", "Item 1"),
                    GetSupplierFilterResponse.Data.Item("Item 2", "Item 2")
                ),
                modifiedby = listOf(
                    GetSupplierFilterResponse.Data.Modifiedby("Modifiedby 1", "Modifiedby 1"),
                    GetSupplierFilterResponse.Data.Modifiedby("Modifiedby 2", "Modifiedby 2")
                ),
                supplier = listOf(
                    GetSupplierFilterResponse.Data.Supplier("Supplier 1", "Supplier 1"),
                    GetSupplierFilterResponse.Data.Supplier("Supplier 2", "Supplier 2")
                )
            )
        )

        // Act
        val result = SupplierMapper().mapSupplierFilter(input)
        val expectedResult = SupplierFilterEntity(
            active = listOf(
                OptionDataBoolean(label = true, value = true),
                OptionDataBoolean(label = false, value = false)
            ),
            supplier = listOf(
                OptionData("Supplier 1", "Supplier 1"),
                OptionData("Supplier 2", "Supplier 2")
            ),
            city = listOf(
                OptionData("city 1", "city 1"),
                OptionData("city 2", "city 2")
            ),
            itemName = listOf(
                OptionData("Item 1", "Item 1"),
                OptionData("Item 2", "Item 2")
            ),
            modifiedBy = listOf(
                OptionData("Modifiedby 1", "Modifiedby 1"),
                OptionData("Modifiedby 2", "Modifiedby 2")
            )
        )

        // Assert
        assertThat(result).isEqualTo(expectedResult)
    }
}