package com.example.forecastapp.data.repository

import com.example.forecastapp.data.network.ApiClient
import com.example.forecastapp.data.network.SimpleResponse
import com.example.forecastapp.data.network.dto.currentWeatherForecast.ForecastDto
import com.example.forecastapp.data.network.dto.fiveDayForecast.FiveDayForecastDto
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getCurrentForecast(): SimpleResponse<ForecastDto> {
        return api.getCurrentForecast()
    }

    suspend fun getFiveDayForecast(): SimpleResponse<FiveDayForecastDto> {
        return api.getFiveDayForecast()
    }
}