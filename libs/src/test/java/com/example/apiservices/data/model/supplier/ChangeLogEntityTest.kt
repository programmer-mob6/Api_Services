package com.example.apiservices.data.model.supplier

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ChangeLogEntityTest {
    @Test
    fun `ChangeLogEntity with default values`() {
        // Act
        val data = ChangeLogEntity()

        // Assert
        assertThat(data.action).isEqualTo("")
        assertThat(data.companyName).isEqualTo("")
        assertThat(data.field).isEqualTo("")
        assertThat(data.lastModified).isEqualTo("")
        assertThat(data.modifiedBy).isEqualTo("")
        assertThat(data.newValue).isEqualTo("")
        assertThat(data.oldValue).isEqualTo("")
    }

    @Test
    fun `ChangeLogEntity with custom values`() {
        val data = ChangeLogEntity(
            action = "action",
            companyName = "companyName",
            field = "field",
            lastModified = "lastModified",
            modifiedBy = "modifiedBy",
            newValue = "newValue",
            oldValue = "oldValue"
        )

        assertThat(data.action).isEqualTo("action")
        assertThat(data.companyName).isEqualTo("companyName")
        assertThat(data.field).isEqualTo("field")
        assertThat(data.lastModified).isEqualTo("lastModified")
        assertThat(data.modifiedBy).isEqualTo("modifiedBy")
        assertThat(data.newValue).isEqualTo("newValue")
        assertThat(data.oldValue).isEqualTo("oldValue")
    }
}