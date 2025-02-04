package com.example.apiservices.di

import com.example.apiservices.data.mapper.supplier.SupplierMapper
import com.example.apiservices.data.repository.supplier.SupplierRepository
import com.example.apiservices.data.repository.supplier.SupplierRepositoryImpl
import com.example.apiservices.data.source.network.datasource.supplier.SupplierApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSupplierRepository(
        supplierApiDataSource: SupplierApiDataSource,
        supplierMapper: SupplierMapper,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): SupplierRepository {
        return SupplierRepositoryImpl(
            supplierApiDataSource = supplierApiDataSource,
            supplierMapper = supplierMapper,
            ioDispatcher = ioDispatcher
        )
    }
}