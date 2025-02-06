package com.example.apiservices.domain

import com.example.apiservices.data.repository.supplier.SupplierRepository
import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.GetChangeLogQueryParams
import com.example.apiservices.data.source.network.model.request.GetSupplierQueryParams
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierIsActiveRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierRequestBody
import javax.inject.Inject

class GetSupplierUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke(queryParams: GetSupplierQueryParams) =
        repository.getSupplier(queryParams)
}

class DeleteAssetByIdUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke(body: DeleteSupplierRequestBody) =
        repository.deleteAssetById(body)
}

class AddSupplierUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke(body: PostSupplierRequestBody) = repository.createSupplier(body)
}

class GetSupplierByIdUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke(id: String) =
        repository.getSupplierByID(id)
}

class PutIsActiveSupplierUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke(body: PutSupplierIsActiveRequestBody) =
        repository.putIsActiveSupplier(body)
}

class UpdateSupplierByIdUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke(body: PutSupplierRequestBody) =
        repository.updateSupplier(body)
}

class GetChangeLogUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke(queryParams: GetChangeLogQueryParams) =
        repository.getChangeLog(queryParams)
}

class GetChangeLogFilterUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke() = repository.getChangeLogFilter()
}

class GetSupplierFilterUseCase @Inject constructor(
    private val repository: SupplierRepository
) {
    operator fun invoke() = repository.getSupplierFilter()
}