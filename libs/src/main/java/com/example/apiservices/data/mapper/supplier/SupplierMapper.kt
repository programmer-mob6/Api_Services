package com.example.apiservices.data.mapper.supplier

import com.example.apiservices.data.model.supplier.AddSupplierSuppliedItem
import com.example.apiservices.data.model.supplier.ChangeLogEntity
import com.example.apiservices.data.model.supplier.SupplierEntity
import com.example.apiservices.data.source.network.model.response.GetChangeLogResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierDetailResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierResponse
import javax.inject.Inject

class SupplierMapper @Inject constructor() {
    fun mapSuppliers(data: List<GetSupplierResponse.Data>): List<SupplierEntity> { //data: List<GetSupplierResponse.Data>
        return data.map {
            SupplierEntity(
                id = it.id,
                companyName = it.companyname,
                suppliedItemSku = it.supplieditem.flatMap { it.sku },
                isActive = if (it.isactive) "Active" else "Inactive",
                city = it.city,
                country = it.country,
                lastModified = it.lastmodified,
                pic = it.pic,
            )
        }
    }

    fun mapSupplier(data: GetSupplierDetailResponse.Data): SupplierEntity { //data: GetSupplierResponse.Data
        return SupplierEntity(
            companyName = data.companyname,
            companyPhone = data.companyphone,
            companyAddress = data.companyaddress,
            suppliedItem = data.supplieditem.map {
                AddSupplierSuppliedItem(
                    itemName = it.item,
                    itemSku = it.sku
                )
            },
//            suppliedItemName = data.supplieditem.map { it.item },
//            suppliedItemSku = data.supplieditem.flatMap { it.sku },
            isActive = if (data.isactive) "Active" else "Inactive",
            city = data.city,
            state = data.state,
            country = data.country,
            zipCode = data.zipcode,
            lastModified = data.lastmodified,
            pic = data.pic,
            picPhone = data.picphone,
            picEmail = data.picemail
        )
    }

    fun mapChangeLog(data: List<GetChangeLogResponse.Data>): List<ChangeLogEntity> {
        return data.map {
            ChangeLogEntity(
                action = it.action,
                companyName = it.companyname,
                field = it.field,
                lastModified = it.lastmodified,
                modifiedBy = it.modifiedby,
                newValue = it.newvalue,
                oldValue = it.oldvalue
            )
        }
    }
}