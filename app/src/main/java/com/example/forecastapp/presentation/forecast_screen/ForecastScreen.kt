package com.example.forecastapp.presentation.forecast_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forecastapp.R
import com.example.forecastapp.model.ForecastForThreeHours
import com.example.forecastapp.presentation.forecast_screen.util.ForecastScreenTopBar
import com.example.forecastapp.presentation.util.CircularProgressBar
import com.example.forecastapp.presentation.util.DownloadState
import com.example.forecastapp.ui.theme.sea
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForecastScreen(
    modifier: Modifier = Modifier,
    viewModel: ForecastScreenViewModel = hiltViewModel(),
    onBackButtonClick: () -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val threeHoursList = state.weather
    Log.d("MainScreen", "MainScreen: is loaded")
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
            Log.d("ForecastScreen", "ForecastScreen: ${threeHoursList.first().dt_txt}")
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    topBar = {
                        ForecastScreenTopBar(
                            city = threeHoursList.first().name,
                            onBackButtonClick = onBackButtonClick
                        )
                    }
                ) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val m = threeHoursList.groupBy { it1 -> it1.dt_txt.takeWhile { it != ' ' } }
                            .map { (date, forecasts) -> date to forecasts }
                        m.forEach { (date, forecasts) ->
                            stickyHeader {
                                Text(
                                    text = formatDate(date),
                                    fontSize = 26.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colors.primary)
                                        .padding(16.dp)
                                )
                            }
                            items(forecasts) { item ->
                                ForecastListItem(item = item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastListItem(
    item: ForecastForThreeHours
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 12.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(
                    id = when (item.weather.main) {
                        "Clear" -> R.drawable.sunny
                        "Rain" -> R.drawable.rain
                        "Clouds" -> R.drawable.cloud
                        else -> R.drawable.cloud
                    }
                ),
                contentDescription = "rain",
                modifier = Modifier
                    .size(70.dp)
                    .padding(10.dp)
            )
            Text(
                text = item.dt_txt.drop(11).dropLast(3),
                fontSize = 20.sp,
            )
            Text(
                text = item.main.temp.toInt().toString() + "°C ",
                fontSize = 20.sp,
            )
            Text(
                text = when (item.weather.main) {
                    "Clear" -> "Ясно"
                    "Rain" -> "Дождь"
                    "Clouds" -> "Облачно"
                    else -> "Облачно"
                },
                fontSize = 20.sp,
            )
        }
    }
}

fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))

    val date = inputFormat.parse(dateString)
    return outputFormat.format(date)
}

@Preview
@Composable
fun ForecastListItemPrev() {
    ForecastListItem(item = ForecastForThreeHours())
}