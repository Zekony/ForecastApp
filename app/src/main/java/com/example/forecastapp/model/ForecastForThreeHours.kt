package com.example.forecastapp.model

import com.example.forecastapp.data.network.dto.fiveDayForecast.forecastForThreeHours.*

data class ForecastForThreeHours(
    var name: String = "",
    val dt_txt: String = "",
    val main: Main = Main(),
    val weather: Weather = Weather(),
)