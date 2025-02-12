package com.example.apiservices.data.repository.supplier

import com.example.apiservices.MainDispatcherRule
import com.example.apiservices.base.Result
import com.example.apiservices.data.mapper.supplier.SupplierMapper
import com.example.apiservices.data.model.supplier.ChangeLogFilterEntity
import com.example.apiservices.data.model.supplier.SupplierFilterEntity
import com.example.apiservices.data.source.network.datasource.supplier.SupplierApiDataSource
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
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SupplierRepositoryImplTest {

    //Class under test
    private lateinit var supplierRepositoryImpl: SupplierRepositoryImpl

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var supplierApiDataSource: SupplierApiDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        supplierRepositoryImpl = SupplierRepositoryImpl(
            supplierApiDataSource = supplierApiDataSource,
            supplierMapper = SupplierMapper(),
            ioDispatcher = mainDispatcherRule.testDispatcher,
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    //getSupplier
    @Test
    fun `(getSupplier) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplier(
                query = GetSupplierQueryParams(itemName = "item")
            )
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getSupplier(
            query = GetSupplierQueryParams(itemName = "item")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getSupplier) when response is not successful should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplier(
                query = GetSupplierQueryParams(itemName = "item")
            )
        } returns mockk {
            coEvery { isSuccessful } returns false
            coEvery { code() } returns 400
            coEvery { body() } returns null
        }

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getSupplier(
            query = GetSupplierQueryParams(itemName = "item")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getSupplier) when response body is null should emit result succes but body is emptylist`() =
        runTest {
            coEvery {
                supplierApiDataSource.getSupplier(
                    query = GetSupplierQueryParams(itemName = "item")
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 200
                coEvery { body() } returns null
            }

            // Act
            val result = supplierRepositoryImpl.getSupplier(
                query = GetSupplierQueryParams(itemName = "item")
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
            Truth.assertThat((result as Result.Success).data).isEmpty()
        }

    @Test
    fun `(getSupplier) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplier(
                query = GetSupplierQueryParams(itemName = "item")
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GetSupplierResponse(data = listOf(GetSupplierResponse.Data(id = "id")))
        }

        // Act
        val result = supplierRepositoryImpl.getSupplier(
            query = GetSupplierQueryParams(itemName = "item")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(getSupplier) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.getSupplier(
                    query = GetSupplierQueryParams(itemName = "item")
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GetSupplierResponse()
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.getSupplier(
                query = GetSupplierQueryParams(itemName = "item")
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //getSupplierByID
    @Test
    fun `(getSupplierByID) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplierDetail(
                id = "id"
            )
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getSupplierByID(
            id = "id"
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getSupplierByID) when response is not successful should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplierDetail(
                id = "id"
            )
        } returns mockk {
            coEvery { isSuccessful } returns false
            coEvery { code() } returns 400
            coEvery { body() } returns null
        }

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getSupplierByID(
            id = "id"
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getSupplierByID) when response body is null should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplierDetail(
                id = "id"
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns null
        }

        // Act
        val result = supplierRepositoryImpl.getSupplierByID(
            id = "id"
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo("Response is not successful")
    }

    @Test
    fun `(getSupplierByID) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplierDetail(
                id = "id"
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GetSupplierDetailResponse(
                data = GetSupplierDetailResponse.Data(
                    companyname = "company"
                )
            )
        }

        // Act
        val result = supplierRepositoryImpl.getSupplierByID(
            id = "id"
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(getSupplierByID) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.getSupplierDetail(
                    id = "id"
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GetSupplierDetailResponse()
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.getSupplierByID(
                id = "id"
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //createSupplier
    @Test
    fun `(createSupplier) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.addSupplier(
                body = PostSupplierRequestBody(companyname = "company")
            )
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.createSupplier(
            body = PostSupplierRequestBody(companyname = "company")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(createSupplier) when response is not successful should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.addSupplier(
                body = PostSupplierRequestBody(companyname = "company")
            )
        } returns mockk {
            coEvery { isSuccessful } returns false
            coEvery { code() } returns 400
            coEvery { body() } returns null
        }

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.createSupplier(
            body = PostSupplierRequestBody(companyname = "company")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(createSupplier) when response body is null should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.addSupplier(
                body = PostSupplierRequestBody(companyname = "company")
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns null
        }

        // Act
        val result = supplierRepositoryImpl.createSupplier(
            body = PostSupplierRequestBody(companyname = "company")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo("Response is not successful")
    }

    @Test
    fun `(createSupplier) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.addSupplier(
                body = PostSupplierRequestBody(companyname = "company")
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 201
            coEvery { body() } returns PostSupplierResponse(
                message = "Success",
                status = "200"
            )
        }

        // Act
        val result = supplierRepositoryImpl.createSupplier(
            body = PostSupplierRequestBody(companyname = "company")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(createSupplier) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.addSupplier(
                    body = PostSupplierRequestBody(companyname = "company")
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns PostSupplierResponse()
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.createSupplier(
                body = PostSupplierRequestBody(companyname = "company")
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //deleteAssetById
    @Test
    fun `(deleteAssetById) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.deleteSupplier(
                body = DeleteSupplierRequestBody(ids = listOf("id"))
            )
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.deleteAssetById(
            body = DeleteSupplierRequestBody(ids = listOf("id"))
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(deleteAssetById) when response is not successful should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.deleteSupplier(
                body = DeleteSupplierRequestBody(ids = listOf("id"))
            )
        } returns mockk {
            coEvery { isSuccessful } returns false
            coEvery { code() } returns 400
            coEvery { body() } returns null
        }

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.deleteAssetById(
            body = DeleteSupplierRequestBody(ids = listOf("id"))
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(deleteAssetById) when response body is null should emit result succes`() = runTest {
        coEvery {
            supplierApiDataSource.deleteSupplier(
                body = DeleteSupplierRequestBody(ids = listOf("id"))
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns null
        }

        // Act
        val result = supplierRepositoryImpl.deleteAssetById(
            body = DeleteSupplierRequestBody(ids = listOf("id"))
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(Unit)
    }

    @Test
    fun `(deleteAssetById) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.deleteSupplier(
                body = DeleteSupplierRequestBody(ids = listOf("id"))
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GeneralResponse(
                message = "Success",
                status = "200"
            )
        }

        // Act
        val result = supplierRepositoryImpl.deleteAssetById(
            body = DeleteSupplierRequestBody(ids = listOf("id"))
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(Unit)
    }

    @Test
    fun `(deleteAssetById) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.deleteSupplier(
                    body = DeleteSupplierRequestBody(ids = listOf("id"))
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GeneralResponse(
                    message = "Success",
                    status = "200"
                )
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.deleteAssetById(
                body = DeleteSupplierRequestBody(ids = listOf("id"))
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //putIsActiveSupplier
    @Test
    fun `(putIsActiveSupplier) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.updateIsActiveSupplier(
                body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
            )
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.putIsActiveSupplier(
            body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(putIsActiveSupplier) when response is not successful should emit result error`() =
        runTest {
            coEvery {
                supplierApiDataSource.updateIsActiveSupplier(
                    body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
                )
            } returns mockk {
                coEvery { isSuccessful } returns false
                coEvery { code() } returns 400
                coEvery { body() } returns null
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.putIsActiveSupplier(
                body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    @Test
    fun `(putIsActiveSupplier) when response body is null should emit result succes`() = runTest {
        coEvery {
            supplierApiDataSource.updateIsActiveSupplier(
                body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns null
        }

        // Act
        val result = supplierRepositoryImpl.putIsActiveSupplier(
            body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(Unit)
    }

    @Test
    fun `(putIsActiveSupplier) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.updateIsActiveSupplier(
                body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GeneralResponse(
                message = "Success",
                status = "200"
            )
        }

        // Act
        val result = supplierRepositoryImpl.putIsActiveSupplier(
            body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(Unit)
    }

    @Test
    fun `(putIsActiveSupplier) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.updateIsActiveSupplier(
                    body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GeneralResponse(
                    message = "Success",
                    status = "200"
                )
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.putIsActiveSupplier(
                body = PutSupplierIsActiveRequestBody(ids = listOf("id"), true)
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //updateSupplier
    @Test
    fun `(updateSupplier) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.updateSUpplier(
                body = PutSupplierRequestBody(id = "id")
            )
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.updateSupplier(
            body = PutSupplierRequestBody(id = "id")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(updateSupplier) when response is not successful should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.updateSUpplier(
                body = PutSupplierRequestBody(id = "id")
            )
        } returns mockk {
            coEvery { isSuccessful } returns false
            coEvery { code() } returns 400
            coEvery { body() } returns null
        }

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.updateSupplier(
            body = PutSupplierRequestBody(id = "id")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(updateSupplier) when response body is null should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.updateSUpplier(
                body = PutSupplierRequestBody(id = "id")
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns null
        }

        // Act
        val result = supplierRepositoryImpl.updateSupplier(
            body = PutSupplierRequestBody(id = "id")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(Unit)
    }

    @Test
    fun `(updateSupplier) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.updateSUpplier(
                body = PutSupplierRequestBody(id = "id")
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GeneralResponse(
                message = "Success",
                status = "200"
            )
        }

        // Act
        val result = supplierRepositoryImpl.updateSupplier(
            body = PutSupplierRequestBody(id = "id")
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(Unit)
    }

    @Test
    fun `(updateSupplier) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.updateSUpplier(
                    body = PutSupplierRequestBody(id = "id")
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GeneralResponse(
                    message = "Success",
                    status = "200"
                )
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.updateSupplier(
                body = PutSupplierRequestBody(id = "id")
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //getChangeLog
    @Test
    fun `(getChangeLog) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getChangeLog(
                query = GetChangeLogQueryParams()
            )
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getChangeLog(
            queryParams = GetChangeLogQueryParams()
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getChangeLog) when response is not successful should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getChangeLog(
                query = GetChangeLogQueryParams()
            )
        } returns mockk {
            coEvery { isSuccessful } returns false
            coEvery { code() } returns 400
            coEvery { body() } returns null
        }

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getChangeLog(
            queryParams = GetChangeLogQueryParams()
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getChangeLog) when response body is null should emit result succes but body is emptylist`() =
        runTest {
            coEvery {
                supplierApiDataSource.getChangeLog(
                    query = GetChangeLogQueryParams()
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 200
                coEvery { body() } returns null
            }

            // Act
            val result = supplierRepositoryImpl.getChangeLog(
                queryParams = GetChangeLogQueryParams()
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
            Truth.assertThat((result as Result.Success).data).isEmpty()
        }

    @Test
    fun `(getChangeLog) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.getChangeLog(
                query = GetChangeLogQueryParams()
            )
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GetChangeLogResponse(
                data = listOf(
                    GetChangeLogResponse.Data(
                        id = "id"
                    )
                )
            )
        }

        // Act
        val result = supplierRepositoryImpl.getChangeLog(
            queryParams = GetChangeLogQueryParams()
        ).first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(getChangeLog) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.getChangeLog(
                    query = GetChangeLogQueryParams()
                )
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GetChangeLogResponse()
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.getChangeLog(
                queryParams = GetChangeLogQueryParams()
            ).first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //getChangeLogFilter
    @Test
    fun `(getChangeLogFilter) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getChangeLogFilter()
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getChangeLogFilter().first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getChangeLogFilter) when response is not successful should emit result error`() =
        runTest {
            coEvery {
                supplierApiDataSource.getChangeLogFilter()
            } returns mockk {
                coEvery { isSuccessful } returns false
                coEvery { code() } returns 400
                coEvery { body() } returns null
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.getChangeLogFilter().first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    @Test
    fun `(getChangeLogFilter) when response body is null should emit result succes but body is emptylist`() =
        runTest {
            coEvery {
                supplierApiDataSource.getChangeLogFilter()
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 200
                coEvery { body() } returns null
            }

            // Act
            val result = supplierRepositoryImpl.getChangeLogFilter().first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
            Truth.assertThat((result as Result.Success).data).isEqualTo(ChangeLogFilterEntity())
        }

    @Test
    fun `(getChangeLogFilter) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.getChangeLogFilter()
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GetChangeLogFilterResponse(
                data = listOf(
                    GetChangeLogFilterResponse.Data(
                        action = listOf(
                            GetChangeLogFilterResponse.Data.Action(
                                label = "label",
                                value = "value"
                            )
                        )
                    )
                )
            )
        }

        // Act
        val result = supplierRepositoryImpl.getChangeLogFilter().first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(getChangeLogFilter) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.getChangeLogFilter()
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GetChangeLogFilterResponse()
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.getChangeLogFilter().first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    //getSupplierFilter
    @Test
    fun `(getSupplierFilter) when data source throw error should emit result error`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplierFilter()
        } throws Exception("Response is not successful")

        val expectedMsg = "Response is not successful"

        // Act
        val result = supplierRepositoryImpl.getSupplierFilter().first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
        Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
    }

    @Test
    fun `(getSupplierFilter) when response is not successful should emit result error`() =
        runTest {
            coEvery {
                supplierApiDataSource.getSupplierFilter()
            } returns mockk {
                coEvery { isSuccessful } returns false
                coEvery { code() } returns 400
                coEvery { body() } returns null
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.getSupplierFilter().first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }

    @Test
    fun `(getSupplierFilter) when response body is null should emit result succes but body is emptylist`() =
        runTest {
            coEvery {
                supplierApiDataSource.getSupplierFilter()
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 200
                coEvery { body() } returns null
            }

            // Act
            val result = supplierRepositoryImpl.getSupplierFilter().first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
            Truth.assertThat((result as Result.Success).data).isEqualTo(SupplierFilterEntity())
        }

    @Test
    fun `(getSupplierFilter) when response is successful should emit result success`() = runTest {
        coEvery {
            supplierApiDataSource.getSupplierFilter()
        } returns mockk {
            coEvery { isSuccessful } returns true
            coEvery { code() } returns 200
            coEvery { body() } returns GetSupplierFilterResponse(
                data = listOf(
                    GetSupplierFilterResponse.Data(
                        city = listOf(
                            GetSupplierFilterResponse.Data.City(
                                label = "label",
                                value = "value"
                            )
                        )
                    )
                )
            )
        }

        // Act
        val result = supplierRepositoryImpl.getSupplierFilter().first()

        // Assert
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(getSupplierFilter) when response is successful but the code not 200 should emit result success`() =
        runTest {
            coEvery {
                supplierApiDataSource.getSupplierFilter()
            } returns mockk {
                coEvery { isSuccessful } returns true
                coEvery { code() } returns 400
                coEvery { body() } returns GetSupplierFilterResponse()
            }

            val expectedMsg = "Response is not successful"

            // Act
            val result = supplierRepositoryImpl.getSupplierFilter().first()

            // Assert
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo(expectedMsg)
        }
}