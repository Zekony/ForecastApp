package com.example.forecastapp.presentation.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forecastapp.data.network.SimpleResponse
import com.example.forecastapp.data.network.dto.currentWeatherForecast.toForecast
import com.example.forecastapp.data.repository.ForecastRepository
import com.example.forecastapp.presentation.details_screen.util.DetailsScreenState
import com.example.forecastapp.presentation.util.DownloadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()

    init {
        getCurrentWeather()
    }

    fun getCurrentWeather() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    downloadState = DownloadState.Loading,
                )
            }
            val request = repository.getCurrentForecast()
            when (request.status) {
                SimpleResponse.Status.Success -> _state.update {
                    it.copy(
                        downloadState = DownloadState.Success,
                        currentWeather = request.body.toForecast()
                    )
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
