package com.example.apiservices.data.source.network.services

import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SupplierApi {
    @GET("suppliers/{id}")
    suspend fun getSuppliersDetail(
        @Path("id") id: String
    ): Response<GetSupplierDetailResponse>

    @GET("suppliers")
    suspend fun getSuplliers(
        @QueryMap queryMap: Map<String, String?> = mapOf()
    ): Response<GetSupplierResponse>

    @POST("suppliers")
    suspend fun addSupplier(
        @Body body: PostSupplierRequestBody,
    ): Response<PostSupplierResponse>

    @HTTP(method = "DELETE", path = "suppliers", hasBody = true)
    suspend fun deleteSupplier(
        @Body body: DeleteSupplierRequestBody
    ): Response<GeneralResponse>

    @PUT("suppliers/activestatus")
    suspend fun updateActiveStatus(
        @Body body: PutSupplierIsActiveRequestBody,
    ): Response<GeneralResponse>

    @PUT("suppliers")
    suspend fun updateSupplier(
        @Body body: PutSupplierRequestBody,
    ): Response<GeneralResponse>

    @GET("changelog")
    suspend fun getChangeLog(
        @QueryMap queryMap: Map<String, String?> = mapOf()
    ): Response<GetChangeLogResponse>

    @GET("changelog/options")
    suspend fun getChangeLogFilter(
        @QueryMap queryMap: Map<String, String?> = mapOf()
    ): Response<GetChangeLogFilterResponse>

    @GET("suppliers/options")
    suspend fun getSupplierFilter(
        @QueryMap queryMap: Map<String, String?> = mapOf()
    ): Response<GetSupplierFilterResponse>
}