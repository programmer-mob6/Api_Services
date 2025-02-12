package com.example.apiservices.domain

import com.example.apiservices.base.Result
import com.example.apiservices.data.model.supplier.ChangeLogEntity
import com.example.apiservices.data.model.supplier.ChangeLogFilterEntity
import com.example.apiservices.data.model.supplier.SupplierEntity
import com.example.apiservices.data.model.supplier.SupplierFilterEntity
import com.example.apiservices.data.repository.supplier.SupplierRepository
import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.GetChangeLogQueryParams
import com.example.apiservices.data.source.network.model.request.GetSupplierQueryParams
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierIsActiveRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierRequestBody
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class SupplierUseCaseTest {
    //Class under test
    private lateinit var getSupplierUseCase: GetSupplierUseCase
    private lateinit var deleteAssetByIdUseCase: DeleteAssetByIdUseCase
    private lateinit var addSupplierUseCase: AddSupplierUseCase
    private lateinit var getSupplierByIdUseCase: GetSupplierByIdUseCase
    private lateinit var putIsActiveSupplierUseCase: PutIsActiveSupplierUseCase
    private lateinit var updateSupplierByIdUseCase: UpdateSupplierByIdUseCase
    private lateinit var getChangeLogUseCase: GetChangeLogUseCase
    private lateinit var getChangeLogFilterUseCase: GetChangeLogFilterUseCase
    private lateinit var getSupplierFilterUseCase: GetSupplierFilterUseCase

    @MockK
    private lateinit var repository: SupplierRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getSupplierUseCase = GetSupplierUseCase(repository)
        deleteAssetByIdUseCase = DeleteAssetByIdUseCase(repository)
        addSupplierUseCase = AddSupplierUseCase(repository)
        getSupplierByIdUseCase = GetSupplierByIdUseCase(repository)
        putIsActiveSupplierUseCase = PutIsActiveSupplierUseCase(repository)
        updateSupplierByIdUseCase = UpdateSupplierByIdUseCase(repository)
        getChangeLogUseCase = GetChangeLogUseCase(repository)
        getChangeLogFilterUseCase = GetChangeLogFilterUseCase(repository)
        getSupplierFilterUseCase = GetSupplierFilterUseCase(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    //GetSupplierUseCase
    @Test
    fun `(GetSupplierUseCase) invoke calls getBorrowBorrowedAssetList on repository`() = runTest {
        coEvery {
            repository.getSupplier(
                query = GetSupplierQueryParams()
            )
        } returns flowOf(Result.Success(listOf(SupplierEntity())))

        val result = getSupplierUseCase.invoke(
            queryParams = GetSupplierQueryParams()
        ).first()

        coVerify {
            repository.getSupplier(
                query = GetSupplierQueryParams()
            )
        }
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(GetSupplierUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.getSupplier(
                    query = GetSupplierQueryParams()
                )
            } returns flowOf(Result.Error("error"))

            val result = getSupplierUseCase.invoke(
                queryParams = GetSupplierQueryParams()
            ).first()

            coVerify {
                repository.getSupplier(
                    query = GetSupplierQueryParams()
                )
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //DeleteAssetByIdUseCase
    @Test
    fun `(DeleteAssetByIdUseCase) invoke calls getBorrowBorrowedAssetList on repository`() =
        runTest {
            coEvery {
                repository.deleteAssetById(
                    body = DeleteSupplierRequestBody()
                )
            } returns flowOf(Result.Success(Unit))

            val result = deleteAssetByIdUseCase.invoke(
                body = DeleteSupplierRequestBody()
            ).first()

            coVerify {
                repository.deleteAssetById(
                    body = DeleteSupplierRequestBody()
                )
            }
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        }

    @Test
    fun `(DeleteAssetByIdUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.deleteAssetById(
                    body = DeleteSupplierRequestBody()
                )
            } returns flowOf(Result.Error("error"))

            val result = deleteAssetByIdUseCase.invoke(
                body = DeleteSupplierRequestBody()
            ).first()

            coVerify {
                repository.deleteAssetById(
                    body = DeleteSupplierRequestBody()
                )
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //AddSupplierUseCase
    @Test
    fun `(AddSupplierUseCase) invoke calls getBorrowBorrowedAssetList on repository`() = runTest {
        coEvery {
            repository.createSupplier(
                body = PostSupplierRequestBody()
            )
        } returns flowOf(Result.Success(Unit))

        val result = addSupplierUseCase.invoke(
            body = PostSupplierRequestBody()
        ).first()

        coVerify {
            repository.createSupplier(
                body = PostSupplierRequestBody()
            )
        }
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(AddSupplierUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.createSupplier(
                    body = PostSupplierRequestBody()
                )
            } returns flowOf(Result.Error("error"))

            val result = addSupplierUseCase.invoke(
                body = PostSupplierRequestBody()
            ).first()

            coVerify {
                repository.createSupplier(
                    body = PostSupplierRequestBody()
                )
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //GetSupplierByIdUseCase
    @Test
    fun `(GetSupplierByIdUseCase) invoke calls getBorrowBorrowedAssetList on repository`() =
        runTest {
            coEvery {
                repository.getSupplierByID(
                    id = "id"
                )
            } returns flowOf(Result.Success(SupplierEntity()))

            val result = getSupplierByIdUseCase.invoke(
                id = "id"
            ).first()

            coVerify {
                repository.getSupplierByID(
                    id = "id"
                )
            }
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        }

    @Test
    fun `(GetSupplierByIdUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.getSupplierByID(
                    id = "id"
                )
            } returns flowOf(Result.Error("error"))

            val result = getSupplierByIdUseCase.invoke(
                id = "id"
            ).first()

            coVerify {
                repository.getSupplierByID(
                    id = "id"
                )
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //PutIsActiveSupplierUseCase
    @Test
    fun `(PutIsActiveSupplierUseCase) invoke calls getBorrowBorrowedAssetList on repository`() =
        runTest {
            coEvery {
                repository.putIsActiveSupplier(
                    body = PutSupplierIsActiveRequestBody()
                )
            } returns flowOf(Result.Success(Unit))

            val result = putIsActiveSupplierUseCase.invoke(
                body = PutSupplierIsActiveRequestBody()
            ).first()

            coVerify {
                repository.putIsActiveSupplier(
                    body = PutSupplierIsActiveRequestBody()
                )
            }
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        }

    @Test
    fun `(PutIsActiveSupplierUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.putIsActiveSupplier(
                    body = PutSupplierIsActiveRequestBody()
                )
            } returns flowOf(Result.Error("error"))

            val result = putIsActiveSupplierUseCase.invoke(
                body = PutSupplierIsActiveRequestBody()
            ).first()

            coVerify {
                repository.putIsActiveSupplier(
                    body = PutSupplierIsActiveRequestBody()
                )
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //UpdateSupplierByIdUseCase
    @Test
    fun `(UpdateSupplierByIdUseCase) invoke calls getBorrowBorrowedAssetList on repository`() =
        runTest {
            coEvery {
                repository.updateSupplier(
                    body = PutSupplierRequestBody()
                )
            } returns flowOf(Result.Success(Unit))

            val result = updateSupplierByIdUseCase.invoke(
                body = PutSupplierRequestBody()
            ).first()

            coVerify {
                repository.updateSupplier(
                    body = PutSupplierRequestBody()
                )
            }
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        }

    @Test
    fun `(UpdateSupplierByIdUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.updateSupplier(
                    body = PutSupplierRequestBody()
                )
            } returns flowOf(Result.Error("error"))

            val result = updateSupplierByIdUseCase.invoke(
                body = PutSupplierRequestBody()
            ).first()

            coVerify {
                repository.updateSupplier(
                    body = PutSupplierRequestBody()
                )
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //GetChangeLogUseCase
    @Test
    fun `(GetChangeLogUseCase) invoke calls getBorrowBorrowedAssetList on repository`() = runTest {
        coEvery {
            repository.getChangeLog(
                queryParams = GetChangeLogQueryParams()
            )
        } returns flowOf(Result.Success(listOf(ChangeLogEntity())))

        val result = getChangeLogUseCase.invoke(
            queryParams = GetChangeLogQueryParams()
        ).first()

        coVerify {
            repository.getChangeLog(
                queryParams = GetChangeLogQueryParams()
            )
        }
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `(GetChangeLogUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.getChangeLog(
                    queryParams = GetChangeLogQueryParams()
                )
            } returns flowOf(Result.Error("error"))

            val result = getChangeLogUseCase.invoke(
                queryParams = GetChangeLogQueryParams()
            ).first()

            coVerify {
                repository.getChangeLog(
                    queryParams = GetChangeLogQueryParams()
                )
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //GetChangeLogFilterUseCase
    @Test
    fun `(GetChangeLogFilterUseCase) invoke calls getBorrowBorrowedAssetList on repository`() =
        runTest {
            coEvery {
                repository.getChangeLogFilter()
            } returns flowOf(Result.Success(ChangeLogFilterEntity()))

            val result = getChangeLogFilterUseCase.invoke().first()

            coVerify {
                repository.getChangeLogFilter()
            }
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        }

    @Test
    fun `(GetChangeLogFilterUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.getChangeLogFilter()
            } returns flowOf(Result.Error("error"))

            val result = getChangeLogFilterUseCase.invoke().first()

            coVerify {
                repository.getChangeLogFilter()
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }

    //GetSupplierFilterUseCase
    @Test
    fun `(GetSupplierFilterUseCase) invoke calls getBorrowBorrowedAssetList on repository`() =
        runTest {
            coEvery {
                repository.getSupplierFilter()
            } returns flowOf(Result.Success(SupplierFilterEntity()))

            val result = getSupplierFilterUseCase.invoke().first()

            coVerify {
                repository.getSupplierFilter()
            }
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        }

    @Test
    fun `(GetSupplierFilterUseCase) when repository give RESULT_ERROR should return RESULT_ERROR`() =
        runTest {
            coEvery {
                repository.getSupplierFilter()
            } returns flowOf(Result.Error("error"))

            val result = getSupplierFilterUseCase.invoke().first()

            coVerify {
                repository.getSupplierFilter()
            }
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).message).isEqualTo("error")
        }
}