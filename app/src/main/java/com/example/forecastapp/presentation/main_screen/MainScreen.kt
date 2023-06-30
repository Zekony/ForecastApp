package com.example.forecastapp.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.example.forecastapp.R
import com.example.forecastapp.presentation.main_screen.util.MainScreenTopBar
import com.example.forecastapp.presentation.util.CircularProgressBar
import com.example.forecastapp.presentation.util.DownloadState
import com.example.forecastapp.ui.theme.ForecastAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel(),
    toDetailsScreen: () -> Unit,
    toForecastScreen: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    val forecast = state.currentWeather
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                MainScreenTopBar(
                    city = forecast.name
                )
            }
        ) {
            when (state.downloadState) {
                DownloadState.Error -> {
                    Column(
                        modifier = modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.loading_error),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = viewModel::getCurrentWeather) {
                            Text(
                                text = stringResource(id = R.string.try_again),
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                DownloadState.Loading -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressBar()
                    }
                }
                DownloadState.Success -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = modifier.height(100.dp))
                            Text(
                                text = forecast.main.temp.toInt().toString() + "°C",
                                fontSize = 50.sp,
                                color = Color.White
                            )
                            val desc =
                                forecast.weather.first().description.replaceFirstChar { it.uppercase() }
                            Text(
                                text = desc, fontSize = 24.sp, color = Color.White
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,

                            ) {
                            LottieAnimation(
                                modifier = Modifier,
                                weather = forecast.weather.first().main
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 20.dp)
                            ) {
                                Button(
                                    onClick = toDetailsScreen, modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .clip(RoundedCornerShape(40.dp))
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.to_details_screen),
                                        fontSize = 18.sp
                                    )
                                }
                                Button(
                                    onClick = toForecastScreen, modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(40.dp))
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.to_forecast_screen),
                                        fontSize = 18.sp
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LottieAnimation(modifier: Modifier = Modifier, weather: String) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            when (weather) {
                "Clear" -> R.raw.sunny
                "Rain" -> R.raw.rain
                "Clouds" -> R.raw.cloud
                else -> R.raw.cloud
            }
        )
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    ForecastAppTheme {
        MainScreen(
            toDetailsScreen = {},
            toForecastScreen = {}
        )
    }

}
