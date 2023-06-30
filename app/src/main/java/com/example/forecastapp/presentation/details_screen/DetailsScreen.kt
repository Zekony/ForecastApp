package com.example.forecastapp.presentation.details_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forecastapp.R
import com.example.forecastapp.presentation.details_screen.util.DetailsScreenTopBar
import com.example.forecastapp.presentation.util.CircularProgressBar
import com.example.forecastapp.presentation.util.DownloadState
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackButtonClick: () -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val forecast = state.currentWeather

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                DetailsScreenTopBar(
                    city = forecast.name,
                    onBackButtonClick = onBackButtonClick
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
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {

                        Spacer(modifier = modifier.height(40.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                TextCard(
                                    name = "Ощущается",
                                    info = forecast.main.feels_like.toInt().toString() + "°C"
                                )
                                TextCard(
                                    name = "Влажность",
                                    info = forecast.main.humidity.toString() + " %"
                                )
                                TextCard(
                                    name = "Давление",
                                    info = forecast.main.pressure.toString() + " мм рт. ст."
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                TextCard(
                                    name = "Скорость ветра",
                                    info = forecast.wind.speed.toString() + " км/ч"
                                )
                                TextCard(
                                    name = "Восход",
                                    info = convertUnixTimestampToTime(forecast.sys.sunrise)
                                )
                                TextCard(
                                    name = "Закат",
                                    info = convertUnixTimestampToTime(forecast.sys.sunset)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextCard(name: String, info: String) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(16.dp)
            .defaultMinSize(minWidth = 140.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = name, fontSize = 16.sp)
            Text(text = info, fontSize = 16.sp)
        }
    }
}

fun convertUnixTimestampToTime(unixTimestamp: Long): String {
    val date = Date(unixTimestamp * 1000L)
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(date)
}

@Preview
@Composable
fun TextCardPrev() {
    TextCard(name = "Ощущается", info = "36?С")
}