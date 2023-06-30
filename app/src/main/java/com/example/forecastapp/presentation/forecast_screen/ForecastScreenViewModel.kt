package com.example.forecastapp.presentation.forecast_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forecastapp.data.network.SimpleResponse
import com.example.forecastapp.data.network.dto.fiveDayForecast.forecastForThreeHours.toForecastForThreeHours
import com.example.forecastapp.data.repository.ForecastRepository
import com.example.forecastapp.presentation.forecast_screen.util.ForecastScreenState
import com.example.forecastapp.presentation.util.DownloadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ForecastScreenState())
    val state = _state.asStateFlow()

    init {
        getCurrentWeather()
    }

    fun getCurrentWeather() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    downloadState = DownloadState.Loading
                )
            }
            val request = repository.getFiveDayForecast()
            when (request.status) {
                SimpleResponse.Status.Success -> {
                    val threeHoursList = request.body.list.map { it.toForecastForThreeHours() }
                        .map { it.copy(name = request.body.city.name) }
                    _state.update {
                        it.copy(
                            downloadState = DownloadState.Success,
                            weather = threeHoursList
                        )
                    }
                }
                SimpleResponse.Status.Failure -> _state.update {
                    it.copy(
                        downloadState = DownloadState.Error
                    )
                }
            }
        }
    }
}