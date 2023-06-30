package com.example.forecastapp.presentation.main_screen.util

import com.example.forecastapp.model.Forecast
import com.example.forecastapp.presentation.util.DownloadState

data class MainScreenState(
    val downloadState: DownloadState = DownloadState.Loading,
    val currentWeather: Forecast = Forecast()
)
