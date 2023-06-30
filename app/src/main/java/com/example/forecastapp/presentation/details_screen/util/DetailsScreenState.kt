package com.example.forecastapp.presentation.details_screen.util

import com.example.forecastapp.model.Forecast
import com.example.forecastapp.presentation.util.DownloadState

data class DetailsScreenState(
    val downloadState: DownloadState = DownloadState.Loading,
    val currentWeather: Forecast = Forecast()
)