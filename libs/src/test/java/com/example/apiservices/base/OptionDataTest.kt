package com.example.apiservices.base

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class OptionDataTest {
    @Test
    fun `OptionData with default values`() {
        // Act
        val data = OptionData()

        // Assert
        assertThat(data.label).isEqualTo("")
        assertThat(data.value).isEqualTo("")
    }

    @Test
    fun `OptionData with custom values`() {
        // Act
        val data = OptionData("label A", "value A")

        // Assert
        assertThat(data.label).isEqualTo("label A")
        assertThat(data.value).isEqualTo("value A")
    }
}