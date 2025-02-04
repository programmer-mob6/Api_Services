package com.example.apiservices.data.mapper.supplier

import com.example.apiservices.data.model.supplier.SupplierEntity
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
//            id = data.id,
            companyName = data.companyname,
            companyPhone = data.companyphone,
            companyAddress = data.companyaddress,
            suppliedItemName = data.supplieditem.map { it.item },
            suppliedItemSku = data.supplieditem.flatMap { it.sku },
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
//        SupplierEntity(
//            id = "id1",
//            companyName = "PT. ABC Indonesia", //it.companyName
//            suppliedItemName = listOf("Laptop Asus", "Laptop Lenovo"),
//            suppliedItemSku = listOf("M1403", "P1412", "TP3402"),
//            isActive = "Active", //bikin fungsi dari boolean jadi string
//            city = "Jakarta",
//            country = "Indonesia",
//            lastModified = "2024-08-15T11:36:50.247Z",
//            pic = "Akulah sang PIC",
//            companyPhone = "(+62)21 6672812",
//            companyAddress = "Jalan Villa Gading Indah Blok A1 No.1 Ruko Boulevard",
//            zipCode = "13440",
//            picPhone = "(+62)21 6672812",
//            picEmail = "Budiu@mailnesia.com"
//        )
    }


}