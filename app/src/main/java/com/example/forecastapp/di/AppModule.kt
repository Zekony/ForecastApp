package com.example.forecastapp.di

import android.content.Context
import com.example.forecastapp.common.Constants
import com.example.forecastapp.data.network.ApiClient
import com.example.forecastapp.data.network.ApiService
import com.example.forecastapp.data.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClient(apiService: ApiService): ApiClient {
        return ApiClient(apiService)
    }

    @Provides
    @Singleton
    fun provideForecastRepository(
        api: ApiClient
    ): ForecastRepository {
        return ForecastRepository(api)
    }
}