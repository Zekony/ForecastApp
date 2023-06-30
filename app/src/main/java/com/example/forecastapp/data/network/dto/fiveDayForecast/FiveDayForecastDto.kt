package com.example.forecastapp.data.network.dto.fiveDayForecast

import com.example.forecastapp.data.network.dto.fiveDayForecast.forecastForThreeHours.ForecastForThreeHoursDto

data class FiveDayForecastDto(
    val city: City = City(),
    val cnt: Int = 0,
    val cod: Int = 0,
    val list: List<ForecastForThreeHoursDto> = listOf(),
    val message: Int = 0
)