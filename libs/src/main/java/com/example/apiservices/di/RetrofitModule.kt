package com.example.apiservices.di

import android.content.Context
import com.example.apiservices.data.source.network.services.SupplierApi
import com.example.apiservices.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideSupplierApi(okHttpClient: OkHttpClient): SupplierApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SupplierApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            // Just for development purpose cause the api is so f*cking slow
            .connectTimeout(10, java.util.concurrent.TimeUnit.MINUTES)
            .readTimeout(10, java.util.concurrent.TimeUnit.MINUTES)
            .writeTimeout(10, java.util.concurrent.TimeUnit.MINUTES)
            .retryOnConnectionFailure(true) // Automatically retry when connection failed
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}