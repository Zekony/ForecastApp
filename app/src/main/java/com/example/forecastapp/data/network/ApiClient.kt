package com.example.forecastapp.data.network

import android.util.Log
import com.example.forecastapp.data.network.dto.currentWeatherForecast.ForecastDto
import com.example.forecastapp.data.network.dto.fiveDayForecast.FiveDayForecastDto
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(private val service: ApiService) {

    suspend fun getCurrentForecast(): SimpleResponse<ForecastDto> {
        return safeApiCall { service.getCurrentWeather() }
    }

    suspend fun getFiveDayForecast():SimpleResponse<FiveDayForecastDto> {
        return safeApiCall { service.getFiveDaysForecast() }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            Log.d("ApiClient", "Response was not successful ${e.message}")
            SimpleResponse.failure(e)
        }
    }
}