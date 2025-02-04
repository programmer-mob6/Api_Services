package com.example.apiservices.di

import com.example.apiservices.data.source.network.datasource.supplier.SupplierApiDataSource
import com.example.apiservices.data.source.network.datasource.supplier.SupplierApiDataSourceImpl
import com.example.apiservices.data.source.network.services.SupplierApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideSupplierApiDataSource(supplierAPI: SupplierApi): SupplierApiDataSource {
        return SupplierApiDataSourceImpl(supplierAPI = supplierAPI)
    }
}