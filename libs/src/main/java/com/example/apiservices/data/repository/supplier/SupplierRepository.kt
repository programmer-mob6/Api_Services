package com.example.apiservices.data.repository.supplier

import com.example.apiservices.base.Result
import com.example.apiservices.data.model.supplier.ChangeLogEntity
import com.example.apiservices.data.model.supplier.ChangeLogFilterEntity
import com.example.apiservices.data.model.supplier.SupplierEntity
import com.example.apiservices.data.model.supplier.SupplierFilterEntity
import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.GetChangeLogQueryParams
import com.example.apiservices.data.source.network.model.request.GetSupplierQueryParams
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierIsActiveRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierRequestBody
import kotlinx.coroutines.flow.Flow

interface SupplierRepository {
    fun getSupplier(query: GetSupplierQueryParams): Flow<Result<List<SupplierEntity>>>

    fun getSupplierByID(id: String): Flow<Result<SupplierEntity>>

    fun createSupplier(body: PostSupplierRequestBody): Flow<Result<Unit>>

    fun deleteAssetById(body: DeleteSupplierRequestBody): Flow<Result<Unit>>

    fun putIsActiveSupplier(body: PutSupplierIsActiveRequestBody): Flow<Result<Unit>>

    fun updateSupplier(body: PutSupplierRequestBody): Flow<Result<Unit>>

    fun getChangeLog(queryParams: GetChangeLogQueryParams): Flow<Result<List<ChangeLogEntity>>>

    fun getChangeLogFilter(): Flow<Result<ChangeLogFilterEntity>>

    fun getSupplierFilter(): Flow<Result<SupplierFilterEntity>>
}