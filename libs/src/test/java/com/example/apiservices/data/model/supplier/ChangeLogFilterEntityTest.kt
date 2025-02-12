package com.example.apiservices.data.model.supplier

import com.example.apiservices.base.OptionData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ChangeLogFilterEntityTest {
    @Test
    fun `ChangeLogFilterEntity with default values`() {
        // Act
        val entity = ChangeLogFilterEntity()

        // Assert
        assertThat(entity.action).isEmpty()
        assertThat(entity.field).isEmpty()
        assertThat(entity.modifiedBy).isEmpty()
    }

    @Test
    fun `ChangeLogEntity with custom values`() {
        // Arrange
        val actionOptions = listOf(OptionData("Create", "Create"), OptionData("Update", "Update"))
        val fieldOptions =
            listOf(OptionData("Status", "Status"), OptionData("Priority", "Priority"))
        val modifiedByOptions =
            listOf(OptionData("User A", "User A"), OptionData("User B", "User B"))

        // Act
        val entity = ChangeLogFilterEntity(
            action = actionOptions,
            field = fieldOptions,
            modifiedBy = modifiedByOptions
        )

        // Assert
        assertThat(entity.action.size).isEqualTo(2)
        assertThat(entity.action[0].label).isEqualTo("Create")
        assertThat(entity.action[0].value).isEqualTo("Create")
        assertThat(entity.action[1].label).isEqualTo("Update")
        assertThat(entity.action[1].value).isEqualTo("Update")

        assertThat(entity.field.size).isEqualTo(2)
        assertThat(entity.field[0].label).isEqualTo("Status")
        assertThat(entity.field[0].value).isEqualTo("Status")
        assertThat(entity.field[1].label).isEqualTo("Priority")
        assertThat(entity.field[1].value).isEqualTo("Priority")

        assertThat(entity.modifiedBy.size).isEqualTo(2)
        assertThat(entity.modifiedBy[0].label).isEqualTo("User A")
        assertThat(entity.modifiedBy[0].value).isEqualTo("User A")
        assertThat(entity.modifiedBy[1].label).isEqualTo("User B")
        assertThat(entity.modifiedBy[1].value).isEqualTo("User B")
    }
}