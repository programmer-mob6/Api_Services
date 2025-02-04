package com.example.apiservices.data.source.network.datasource.supplier

import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.GetSupplierQueryParams
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierIsActiveRequestBody
import com.example.apiservices.data.source.network.model.response.GeneralResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierDetailResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierResponse
import com.example.apiservices.data.source.network.model.response.PostSupplierResponse
import com.example.apiservices.domain.GetSupplierByIdUseCase
import retrofit2.Response

interface SupplierApiDataSource {
    suspend fun getSupplierDetail(
        id: String
    ) : Response<GetSupplierDetailResponse>

    suspend fun getSupplier(
        query: GetSupplierQueryParams
    ) : Response<GetSupplierResponse>

    suspend fun addSupplier(
        body: PostSupplierRequestBody
    ): Response<PostSupplierResponse>

    suspend fun deleteSupplier(
        body: DeleteSupplierRequestBody
    ): Response<GeneralResponse>

    suspend fun updateIsActiveSupplier(
        body: PutSupplierIsActiveRequestBody
    ): Response<GeneralResponse>
}