package com.example.apiservices.data.source.network.datasource.supplier

import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.GetChangeLogQueryParams
import com.example.apiservices.data.source.network.model.request.GetSupplierQueryParams
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierIsActiveRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierRequestBody
import com.example.apiservices.data.source.network.model.response.GeneralResponse
import com.example.apiservices.data.source.network.model.response.GetChangeLogFilterResponse
import com.example.apiservices.data.source.network.model.response.GetChangeLogResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierDetailResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierFilterResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierResponse
import com.example.apiservices.data.source.network.model.response.PostSupplierResponse
import com.example.apiservices.data.source.network.services.SupplierApi
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class SupplierApiDataSourceImplTest {
    // Mock dependencies
    private val supplierAPI: SupplierApi = mockk()
    private lateinit var supplierApiDataSource: SupplierApiDataSourceImpl

    @Before
    fun setUp() {
        supplierApiDataSource = SupplierApiDataSourceImpl(supplierAPI)
    }

    //getSupplierDetail
    @Test
    fun `getSupplierDetail when API call is successful should return response with data`() =
        runTest {
            // Arrange
            val supplierId = "123"
            val expectedResponse = Response.success(GetSupplierDetailResponse())

            coEvery {
                supplierAPI.getSuppliersDetail(id = supplierId)
            } returns expectedResponse

            // Act
            val result = supplierApiDataSource.getSupplierDetail(supplierId)

            // Assert
            coVerify { supplierAPI.getSuppliersDetail(id = supplierId) }
            Truth.assertThat(result).isEqualTo(expectedResponse)
        }

    @Test
    fun `getSupplierDetail when API call fails should throw exception`() = runTest {
        // Arrange
        val supplierId = "123"
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.getSuppliersDetail(id = supplierId)
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.getSupplierDetail(supplierId) }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.getSuppliersDetail(id = supplierId) }
    }

    //getSupplier
    @Test
    fun `getSupplier when API call is successful should return response with data`() = runTest {
        // Arrange
        val query = GetSupplierQueryParams(supplier = "supplier 1")
        val expectedResponse = Response.success(GetSupplierResponse())

        coEvery {
            supplierAPI.getSuplliers(queryMap = query.toQueryMap())
        } returns expectedResponse

        // Act
        val result = supplierApiDataSource.getSupplier(query)

        // Assert
        coVerify { supplierAPI.getSuplliers(queryMap = query.toQueryMap()) }
        Truth.assertThat(result).isEqualTo(expectedResponse)
    }

    @Test
    fun `getSupplier when API call fails should throw exception`() = runTest {
        // Arrange
        val query = GetSupplierQueryParams(supplier = "supplier 1")
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.getSuplliers(queryMap = query.toQueryMap())
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.getSupplier(query) }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.getSuplliers(queryMap = query.toQueryMap()) }
    }

    //addSupplier
    @Test
    fun `addSupplier when API call is successful should return response with data`() = runTest {
        // Arrange
        val body = PostSupplierRequestBody(companyname = "supplier 1")
        val expectedResponse = Response.success(PostSupplierResponse())

        coEvery {
            supplierAPI.addSupplier(body = body)
        } returns expectedResponse

        // Act
        val result = supplierApiDataSource.addSupplier(body)

        // Assert
        coVerify { supplierAPI.addSupplier(body = body) }
        Truth.assertThat(result).isEqualTo(expectedResponse)
    }

    @Test
    fun `addSupplier when API call fails should throw exception`() = runTest {
        // Arrange
        val body = PostSupplierRequestBody(companyname = "supplier 1")
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.addSupplier(body = body)
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.addSupplier(body) }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.addSupplier(body = body) }
    }

    //deleteSupplier
    @Test
    fun `deleteSupplier when API call is successful should return response with data`() = runTest {
        // Arrange
        val body = DeleteSupplierRequestBody(ids = listOf("id"))
        val expectedResponse = Response.success(GeneralResponse())

        coEvery {
            supplierAPI.deleteSupplier(body = body)
        } returns expectedResponse

        // Act
        val result = supplierApiDataSource.deleteSupplier(body)

        // Assert
        coVerify { supplierAPI.deleteSupplier(body = body) }
        Truth.assertThat(result).isEqualTo(expectedResponse)
    }

    @Test
    fun `deleteSupplier when API call fails should throw exception`() = runTest {
        // Arrange
        val body = DeleteSupplierRequestBody(ids = listOf("id"))
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.deleteSupplier(body = body)
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.deleteSupplier(body) }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.deleteSupplier(body = body) }
    }

    //updateIsActiveSupplier
    @Test
    fun `updateIsActiveSupplier when API call is successful should return response with data`() =
        runTest {
            // Arrange
            val body = PutSupplierIsActiveRequestBody(ids = listOf("id"))
            val expectedResponse = Response.success(GeneralResponse())

            coEvery {
                supplierAPI.updateActiveStatus(body = body)
            } returns expectedResponse

            // Act
            val result = supplierApiDataSource.updateIsActiveSupplier(body)

            // Assert
            coVerify { supplierAPI.updateActiveStatus(body = body) }
            Truth.assertThat(result).isEqualTo(expectedResponse)
        }

    @Test
    fun `updateIsActiveSupplier when API call fails should throw exception`() = runTest {
        // Arrange
        val body = PutSupplierIsActiveRequestBody(ids = listOf("id"))
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.updateActiveStatus(body = body)
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.updateIsActiveSupplier(body) }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.updateActiveStatus(body = body) }
    }

    //updateSUpplier
    @Test
    fun `updateSUpplier when API call is successful should return response with data`() = runTest {
        // Arrange
        val body = PutSupplierRequestBody(id = "id")
        val expectedResponse = Response.success(GeneralResponse())

        coEvery {
            supplierAPI.updateSupplier(body = body)
        } returns expectedResponse

        // Act
        val result = supplierApiDataSource.updateSUpplier(body)

        // Assert
        coVerify { supplierAPI.updateSupplier(body = body) }
        Truth.assertThat(result).isEqualTo(expectedResponse)
    }

    @Test
    fun `updateSUpplier when API call fails should throw exception`() = runTest {
        // Arrange
        val body = PutSupplierRequestBody(id = "id")
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.updateSupplier(body = body)
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.updateSUpplier(body) }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.updateSupplier(body = body) }
    }

    //getChangeLog
    @Test
    fun `getChangeLog when API call is successful should return response with data`() = runTest {
        // Arrange
        val query = GetChangeLogQueryParams()
        val expectedResponse = Response.success(GetChangeLogResponse())

        coEvery {
            supplierAPI.getChangeLog(queryMap = query.toQueryMap())
        } returns expectedResponse

        // Act
        val result = supplierApiDataSource.getChangeLog(query)

        // Assert
        coVerify { supplierAPI.getChangeLog(queryMap = query.toQueryMap()) }
        Truth.assertThat(result).isEqualTo(expectedResponse)
    }

    @Test
    fun `getChangeLog when API call fails should throw exception`() = runTest {
        // Arrange
        val query = GetChangeLogQueryParams()
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.getChangeLog(queryMap = query.toQueryMap())
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.getChangeLog(query) }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.getChangeLog(queryMap = query.toQueryMap()) }
    }

    //getChangeLogFilter
    @Test
    fun `getChangeLogFilter when API call is successful should return response with data`() =
        runTest {
            // Arrange
            val query = GetChangeLogQueryParams().toQueryMap()
            val expectedResponse = Response.success(GetChangeLogFilterResponse())

            coEvery {
                supplierAPI.getChangeLogFilter(queryMap = query)
            } returns expectedResponse

            // Act
            val result = supplierApiDataSource.getChangeLogFilter()

            // Assert
            coVerify { supplierAPI.getChangeLogFilter(queryMap = query) }
            Truth.assertThat(result).isEqualTo(expectedResponse)
        }

    @Test
    fun `getChangeLogFilter when API call fails should throw exception`() = runTest {
        // Arrange
        val query = GetChangeLogQueryParams().toQueryMap()
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.getChangeLogFilter(queryMap = query)
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.getChangeLogFilter() }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.getChangeLogFilter(queryMap = query) }
    }

    //getSupplierFilter
    @Test
    fun `getSupplierFilter when API call is successful should return response with data`() =
        runTest {
            // Arrange
            val query = GetSupplierQueryParams().toQueryMap()
            val expectedResponse = Response.success(GetSupplierFilterResponse())

            coEvery {
                supplierAPI.getSupplierFilter(queryMap = query)
            } returns expectedResponse

            // Act
            val result = supplierApiDataSource.getSupplierFilter()

            // Assert
            coVerify { supplierAPI.getSupplierFilter(queryMap = query) }
            Truth.assertThat(result).isEqualTo(expectedResponse)
        }

    @Test
    fun `getSupplierFilter when API call fails should throw exception`() = runTest {
        // Arrange
        val query = GetSupplierQueryParams().toQueryMap()
        val exception = IOException("Network Error")

        coEvery {
            supplierAPI.getSupplierFilter(queryMap = query)
        } throws exception

        // Act & Assert
        try {
            runBlocking { supplierApiDataSource.getSupplierFilter() }
            fail("Expected an IOException to be thrown")
        } catch (e: IOException) {
            // Verify exception message
            Truth.assertThat(e).hasMessageThat().isEqualTo("Network Error")
        }

        // Verify API call
        coVerify { supplierAPI.getSupplierFilter(queryMap = query) }
    }
}