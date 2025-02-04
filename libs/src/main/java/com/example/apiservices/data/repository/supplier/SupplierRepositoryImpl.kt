package com.example.apiservices.data.repository.supplier

import android.util.Log
import com.example.apiservices.base.Result
import com.example.apiservices.data.mapper.supplier.SupplierMapper
import com.example.apiservices.data.model.supplier.SupplierEntity
import com.example.apiservices.data.source.network.datasource.supplier.SupplierApiDataSource
import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.GetSupplierQueryParams
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierIsActiveRequestBody
import com.example.apiservices.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SupplierRepositoryImpl @Inject constructor(
    private val supplierApiDataSource: SupplierApiDataSource,
    private val supplierMapper: SupplierMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SupplierRepository {

    override fun getSupplier(query: GetSupplierQueryParams): Flow<Result<List<SupplierEntity>>> = flow {
//        emit(Result.Success(supplierMapper.mapSuppliers()))
        val response = supplierApiDataSource.getSupplier(query)
        val resData = response.body()?.data.orEmpty()

        if (response.isSuccessful && response.code() == 200) {
            emit(Result.Success(supplierMapper.mapSuppliers(resData)))
        } else {
            emit(Result.Error("Response is not successful"))
        }
    }.flowOn(ioDispatcher)

    override fun getSupplierByID(id: String): Flow<Result<SupplierEntity>> = flow{
        val response = supplierApiDataSource.getSupplierDetail(id)
        val isSuccess = response.isSuccessful && response.body() != null && response.code() == 200

        if (isSuccess) {
            emit(Result.Success(supplierMapper.mapSupplier(response.body()!!.data!!)))
        } else {
            emit(Result.Error("Response is not successful"))
        }
//        emit(Result.Success(supplierMapper.mapSupplier()))
    }.flowOn(ioDispatcher)

    override fun createSupplier(body: PostSupplierRequestBody): Flow<Result<Unit>> = flow {
        val response = supplierApiDataSource.addSupplier(body)
        if (response.isSuccessful && response.code() == 201) {
            emit(Result.Success(Unit))
        } else {
            emit(Result.Error("Response is not successful"))
        }
    }.catch {
        emit(Result.Error(it.message))
    }.flowOn(ioDispatcher)

    override fun deleteAssetById(body: DeleteSupplierRequestBody): Flow<Result<Unit>> = flow {
        val response = supplierApiDataSource.deleteSupplier(body)
        if (response.isSuccessful && response.code() == 200) {
            emit(Result.Success(Unit))
        } else {
            emit(Result.Error("Response is not successful"))
        }
    }.catch {
        emit(Result.Error(it.message))
    }.flowOn(ioDispatcher)

    override fun putIsActiveSupplier(body: PutSupplierIsActiveRequestBody): Flow<Result<Unit>> = flow {
        val response = supplierApiDataSource.updateIsActiveSupplier(body)

        if (response.isSuccessful && response.code() == 200) {
            emit(Result.Success(Unit))
        } else {
            emit(Result.Error("Response is not successful"))
        }
    }.catch {
        emit(Result.Error(it.message))
    }.flowOn(ioDispatcher)

    override fun updateSupplier(body: PostSupplierRequestBody): Flow<Result<Unit>> = flow {
        val response = supplierApiDataSource.updateSUpplier(body)
        if (response.isSuccessful && response.code() == 200) {
            emit(Result.Success(Unit))
        } else {
            emit(Result.Error("Response is not successful"))
        }
    }.catch {
        emit(Result.Error(it.message))
    }.flowOn(ioDispatcher)

}