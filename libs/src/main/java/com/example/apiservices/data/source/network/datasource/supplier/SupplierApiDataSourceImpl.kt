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
import retrofit2.Response
import javax.inject.Inject

class SupplierApiDataSourceImpl @Inject constructor(
    private val supplierAPI: SupplierApi
) : SupplierApiDataSource {
    override suspend fun getSupplierDetail(id: String): Response<GetSupplierDetailResponse> {
        return supplierAPI.getSuppliersDetail(id = id)
    }

    override suspend fun getSupplier(query: GetSupplierQueryParams): Response<GetSupplierResponse> {
        return supplierAPI.getSuplliers(query.toQueryMap())
    }

    override suspend fun addSupplier(body: PostSupplierRequestBody): Response<PostSupplierResponse> {
        return supplierAPI.addSupplier(body)
    }

    override suspend fun deleteSupplier(body: DeleteSupplierRequestBody): Response<GeneralResponse> {
        return supplierAPI.deleteSupplier(body)
    }

    override suspend fun updateIsActiveSupplier(body: PutSupplierIsActiveRequestBody): Response<GeneralResponse> {
        return supplierAPI.updateActiveStatus(body)
    }

    override suspend fun updateSUpplier(body: PutSupplierRequestBody): Response<GeneralResponse> {
        return supplierAPI.updateSupplier(body)
    }

    override suspend fun getChangeLog(query: GetChangeLogQueryParams): Response<GetChangeLogResponse> {
        return supplierAPI.getChangeLog(query.toQueryMap())
    }

    override suspend fun getChangeLogFilter(): Response<GetChangeLogFilterResponse> {
        return supplierAPI.getChangeLogFilter(GetChangeLogQueryParams().toQueryMap())
    }

    override suspend fun getSupplierFilter(): Response<GetSupplierFilterResponse> {
        return supplierAPI.getSupplierFilter(GetSupplierQueryParams().toQueryMap())
    }


}