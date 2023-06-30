package com.example.forecastapp.presentation.forecast_screen.util

import com.example.forecastapp.model.ForecastForThreeHours
import com.example.forecastapp.presentation.util.DownloadState

data class ForecastScreenState(
    val downloadState: DownloadState = DownloadState.Loading,
    val weather: List<ForecastForThreeHours> = emptyList()
)