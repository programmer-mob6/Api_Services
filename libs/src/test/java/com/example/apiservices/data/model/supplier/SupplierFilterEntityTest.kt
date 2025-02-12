package com.example.apiservices.data.model.supplier

import com.example.apiservices.base.OptionData
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class SupplierFilterEntityTest {
    @Test
    fun `ChangeLogFilterEntity with default values`() {
        // Act
        val entity = SupplierFilterEntity()

        // Assert
        assertThat(entity.active).isEmpty()
        assertThat(entity.supplier).isEmpty()
        assertThat(entity.city).isEmpty()
        assertThat(entity.itemName).isEmpty()
        assertThat(entity.modifiedBy).isEmpty()
    }

    @Test
    fun `ChangeLogFilterEntity with custom values`() {
        // Act
        val entity = SupplierFilterEntity(
            active = listOf(OptionDataBoolean(true, true)),
            supplier = listOf(OptionData("supplier", "supplier")),
            city = listOf(OptionData("city", "city")),
            itemName = listOf(OptionData("itemName", "itemName")),
            modifiedBy = listOf(OptionData("modifiedBy", "modifiedBy"))
        )

        // Assert
        assertThat(entity.active).isEqualTo(listOf(OptionDataBoolean(true, true)))
        assertThat(entity.supplier).isEqualTo(listOf(OptionData("supplier", "supplier")))
        assertThat(entity.city).isEqualTo(listOf(OptionData("city", "city")))
        assertThat(entity.itemName).isEqualTo(listOf(OptionData("itemName", "itemName")))
        assertThat(entity.modifiedBy).isEqualTo(listOf(OptionData("modifiedBy", "modifiedBy")))
    }
}