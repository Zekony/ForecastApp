package com.example.forecastapp.model

import com.example.forecastapp.data.network.dto.currentWeatherForecast.*

data class Forecast(
    val main: Main = Main(),
    val name: String = "",
    val weather: List<Weather> = listOf(),
    val wind: Wind = Wind(),
    val sys: Sys = Sys(),
)

