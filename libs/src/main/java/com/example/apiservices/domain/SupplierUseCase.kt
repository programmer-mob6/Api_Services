package com.example.apiservices.domain

import com.example.apiservices.data.repository.supplier.SupplierRepository
import com.example.apiservices.data.source.network.model.request.DeleteSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.GetSupplierQueryParams
import com.example.apiservices.data.source.network.model.request.PostSupplierRequestBody
import com.example.apiservices.data.source.network.model.request.PutSupplierIsActiveRequestBody
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