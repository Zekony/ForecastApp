package com.example.forecastapp.data.network

import com.example.forecastapp.data.network.dto.currentWeatherForecast.ForecastDto
import com.example.forecastapp.data.network.dto.fiveDayForecast.FiveDayForecastDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("weather?q=Санкт Петербург,ru&lang=ru&units=metric&appid=d9e6fe2ca9bd114df14262b014663852")
    suspend fun getCurrentWeather(): Response<ForecastDto>

    @GET("forecast?q=Санкт Петербург,ru&lang=ru&units=metric&appid=d9e6fe2ca9bd114df14262b014663852")
    suspend fun getFiveDaysForecast(): Response<FiveDayForecastDto>
}