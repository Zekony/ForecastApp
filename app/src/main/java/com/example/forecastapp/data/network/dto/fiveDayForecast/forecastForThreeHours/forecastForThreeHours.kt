package com.example.forecastapp.data.network.dto.fiveDayForecast.forecastForThreeHours

import com.example.forecastapp.data.network.dto.fiveDayForecast.Clouds
import com.example.forecastapp.model.ForecastForThreeHours

data class ForecastForThreeHoursDto(
    val clouds: Clouds = Clouds(),
    val dt: Int = 0,
    val dt_txt: String = "",
    val main: Main = Main(),
    val pop: Double = 0.0,
    val rain: Rain = Rain(),
    val sys: Sys = Sys(),
    val visibility: Int = 0,
    val weather: List<Weather> = listOf(),
    val wind: Wind = Wind()
)

fun ForecastForThreeHoursDto.toForecastForThreeHours(): ForecastForThreeHours {
    return ForecastForThreeHours(
        main = this.main,
        weather = this.weather.first(),
        dt_txt = this.dt_txt
    )
}