package com.example.forecastapp.data.network.dto.currentWeatherForecast

import com.example.forecastapp.model.Forecast

data class ForecastDto(
    val base: String = "",
    val clouds: Clouds = Clouds(),
    val cod: Int = 0,
    val coord: Coord = Coord(),
    val dt: Int = 0,
    val id: Int = 0,
    val main: Main = Main(),
    val name: String = "",
    val sys: Sys = Sys(),
    val timezone: Int = 0,
    val visibility: Int = 0,
    val weather: List<Weather> = listOf(),
    val wind: Wind = Wind()
)

fun ForecastDto.toForecast(): Forecast {
    return Forecast(
        main = this.main,
        name = this.name,
        weather = this.weather,
        wind = this.wind,
        sys = this.sys
    )
}
