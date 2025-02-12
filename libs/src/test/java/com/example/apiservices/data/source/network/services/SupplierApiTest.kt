package com.example.apiservices.data.source.network.services

import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierRequestBody
import com.example.apiservices.data.source.network.model.response.GetChangeLogFilterResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierFilterResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SupplierApiTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: SupplierApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupplierApi::class.java)
    }

    @After
    fun tearDown() {
        unmockkAll()
        mockWebServer.shutdown()
    }

    @Test
    fun `getSuppliersDetail should return success response`() = runBlocking {
        val mockResponse = MockResponse()
            .setBody(
                """
                {
                    "data": {
                        "companyname":"Supplier A" 
                    }
                }
            """.trimIndent()
            )
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val response = api.getSuppliersDetail("123")

        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.data?.companyname).isEqualTo("Supplier A")
    }

    @Test
    fun `addSupplier should return success response`() = runBlocking {
        val mockResponse = MockResponse()
            .setBody("""{"status":"200"}""")
            .setResponseCode(201)
        mockWebServer.enqueue(mockResponse)

        val requestBody = PostSupplierRequestBody(
            companyname = "Supplier A",
            city = "Jakarta",
            country = "Indonesia",
            state = "DKI",
            zipcode = "12345",
            pic = "John Doe",
            picphone = "08123456789",
            picemail = "john@example.com",
            supplieditem = listOf(
                PostSupplierRequestBody.Supplieditem(
                    item = "Laptop",
                    sku = listOf("LP123")
                )
            ),
            companyphone = "021123456",
            companyaddress = "Jl. Sudirman"
        )

        val response = api.addSupplier(requestBody)

        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.status).isEqualTo("200")
    }

    @Test
    fun `deleteSupplier should return success response`() = runBlocking {
        val mockResponse = MockResponse()
            .setBody("""{"message":"Supplier deleted successfully"}""")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val requestBody = DeleteSupplierRequestBody(ids = listOf("123"))

        val response = api.deleteSupplier(requestBody)

        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.message).isEqualTo("Supplier deleted successfully")
    }

    @Test
    fun `updateSupplier should return success response`() = runBlocking {
        val mockResponse = MockResponse()
            .setBody("""{"status":"updated"}""")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val requestBody = PutSupplierRequestBody(
            id = "123",
            companyname = "Supplier A",
            city = "Jakarta",
            country = "Indonesia",
            state = "DKI",
            zipcode = "12345",
            pic = "John Doe",
            picphone = "08123456789",
            picemail = "john@example.com",
            supplieditem = listOf(
                PutSupplierRequestBody.Supplieditem(
                    item = "Laptop",
                    sku = listOf("LP123")
                )
            ),
            companyphone = "021123456",
            companyaddress = "Jl. Sudirman"
        )

        val response = api.updateSupplier(requestBody)

        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
    }

    @Test
    fun `getSuppliers should return success response`() = runBlocking {
        // Simulasikan respons JSON dari API
        val mockResponse = MockResponse()
            .setBody(
                """
                {
                    "data": [
                        {
                            "id": "1",
                            "companyname": "Supplier A",
                            "city": "Jakarta",
                            "country": "Indonesia"
                        },
                        {
                            "id": "2",
                            "companyname": "Supplier B",
                            "city": "Bandung",
                            "country": "Indonesia"
                        }
                    ]
                }
            """.trimIndent()
            )
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        // Panggil API dengan query kosong
        val response = api.getSuplliers()

        // Verifikasi hasilnya
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.data).isNotEmpty()
        assertThat(response.body()?.data?.size).isEqualTo(2)
        assertThat(response.body()?.data?.first()?.companyname).isEqualTo("Supplier A")
    }

    @Test
    fun `getChangeLog should return success response`() = runBlocking {
        // Simulasikan respons JSON dari API
        val mockResponse = MockResponse()
            .setBody(
                """
                {
                    "data": [
                        {
                            "id": "1",
                            "action": "UPDATE",
                            "modifiedBy": "User A",
                            "lastmodified": "2024-01-01",
                            "field": "status",
                            "oldValue": "Pending",
                            "newValue": "Approved"
                        },
                        {
                            "id": "2",
                            "action": "DELETE",
                            "modifiedBy": "User B",
                            "lastmodified": "2024-02-01",
                            "field": "price",
                            "oldValue": "1000",
                            "newValue": "0"
                        }
                    ]
                }
            """.trimIndent()
            )
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        // Panggil API dengan query kosong
        val response = api.getChangeLog()

        // Verifikasi hasilnya
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.data).isNotEmpty()
        assertThat(response.body()?.data?.size).isEqualTo(2)
        assertThat(response.body()?.data?.first()?.action).isEqualTo("UPDATE")
    }

    @Test
    fun `getChangeLogFilter should return success response`() = runBlocking {
        // Simulasi respons JSON untuk getChangeLogFilter
        val mockResponse = MockResponse()
            .setBody(
                """
                {
                    "data" : [
                        {
                            "action": [
                                {
                                    "label" : "CREATE",
                                    "value" : "CREATE"
                                }
                            ],
                            "field": [
                                {
                                    "label" : "status",
                                    "value" : "status"
                                }
                            ],
                            "modifiedby": [
                                {
                                    "label" : "User A",
                                    "value" : "User A"
                                }
                            ]
                            
                        }
                    ]
                }
            """.trimIndent()
            )
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        // Panggil API
        val response = api.getChangeLogFilter()

        // Verifikasi hasilnya
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.data?.first()?.action?.first()).isEqualTo(
            GetChangeLogFilterResponse.Data.Action(label = "CREATE", value = "CREATE")
        )
        assertThat(response.body()?.data?.first()?.field).isEqualTo(
            listOf(
                GetChangeLogFilterResponse.Data.Field(label = "status", value = "status")
            )
        )
        assertThat(response.body()?.data?.first()?.modifiedby).isEqualTo(
            listOf(
                GetChangeLogFilterResponse.Data.Modifiedby(label = "User A", value = "User A")
            )
        )
    }

    @Test
    fun `getSupplierFilter should return success response`() = runBlocking {
        // Simulasi respons JSON untuk getSupplierFilter
        val mockResponse = MockResponse()
            .setBody(
                """
                {
                    "data" : [
                        {
                            "city": [
                                {
                                    "label" : "Jakarta",
                                    "value" : "Jakarta"
                                }
                            ],
                            "isactive": [
                                {
                                   "label" : true,
                                   "value" : true
                                }
                            ]
                        }
                    ]
                }
            """.trimIndent()
            )
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        // Panggil API
        val response = api.getSupplierFilter()

        // Verifikasi hasilnya
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.data?.first()?.city?.first()).isEqualTo(
            GetSupplierFilterResponse.Data.City(label = "Jakarta", value = "Jakarta")
        )
        assertThat(response.body()?.data?.first()?.isactive?.first()).isEqualTo(
            GetSupplierFilterResponse.Data.Isactive(label = true, value = true)
        )
    }
}