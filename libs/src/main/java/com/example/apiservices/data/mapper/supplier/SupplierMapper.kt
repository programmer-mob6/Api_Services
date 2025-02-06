package com.example.apiservices.data.mapper.supplier

import com.example.apiservices.base.OptionData
import com.example.apiservices.data.model.supplier.AddSupplierSuppliedItem
import com.example.apiservices.data.model.supplier.ChangeLogEntity
import com.example.apiservices.data.model.supplier.ChangeLogFilterEntity
import com.example.apiservices.data.model.supplier.OptionDataBoolean
import com.example.apiservices.data.model.supplier.SupplierEntity
import com.example.apiservices.data.model.supplier.SupplierFilterEntity
import com.example.apiservices.data.source.network.model.response.GetChangeLogFilterResponse
import com.example.apiservices.data.source.network.model.response.GetChangeLogResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierDetailResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierFilterResponse
import com.example.apiservices.data.source.network.model.response.GetSupplierResponse
import javax.inject.Inject

class SupplierMapper @Inject constructor() {
    fun mapSuppliers(data: List<GetSupplierResponse.Data>): List<SupplierEntity> {
        return data.map {
            SupplierEntity(
                id = it.id,
                companyName = it.companyname,
                suppliedItemSku = it.supplieditem.flatMap { data -> data.sku },
                isActive = if (it.isactive) "Active" else "Inactive",
                city = it.city,
                country = it.country,
                lastModified = it.lastmodified,
                pic = it.pic,
            )
        }
    }

    fun mapSupplier(data: GetSupplierDetailResponse.Data): SupplierEntity {
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

    fun mapChangeLogFilter(data: List<GetChangeLogFilterResponse.Data>): ChangeLogFilterEntity {
        return ChangeLogFilterEntity(
            action = data.flatMap { it.action }
                .map { OptionData(label = it.label, value = it.value) },
            field = data.flatMap { it.field }
                .map { OptionData(label = it.label, value = it.value) },
            modifiedBy = data.flatMap { it.modifiedby }
                .map { OptionData(label = it.label, value = it.value) }
        )
    }

    fun mapSupplierFilter(data: List<GetSupplierFilterResponse.Data>): SupplierFilterEntity {
        return SupplierFilterEntity(
            active = data.flatMap { it.isactive }
                .map { OptionDataBoolean(label = it.label, value = it.value) },
            supplier = data.flatMap { it.supplier }
                .map { OptionData(label = it.label, value = it.value) },
            city = data.flatMap { it.city }.map { OptionData(label = it.label, value = it.value) },
            itemName = data.flatMap { it.item }
                .map { OptionData(label = it.label, value = it.value) },
            modifiedBy = data.flatMap { it.modifiedby }
                .map { OptionData(label = it.label, value = it.value) }
        )
    }
}