package com.example.forecastapp.presentation.core

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.forecastapp.R
import com.example.forecastapp.presentation.details_screen.DetailsScreen
import com.example.forecastapp.presentation.forecast_screen.ForecastScreen
import com.example.forecastapp.presentation.main_screen.MainScreen
import com.example.forecastapp.presentation.util.CircularProgressBar
import kotlinx.coroutines.delay

@Composable
fun ForecastAppNavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("main") {
            MainScreen(
                toForecastScreen = { navController.navigate("fiveDayForeCast") },
                toDetailsScreen = { navController.navigate("detail") }
            )
        }
        composable("detail") {
            DetailsScreen(
                onBackButtonClick = navController::navigateUp,
            )
        }
        composable("fiveDayForecast") {
            ForecastScreen(
                onBackButtonClick = navController::navigateUp
            )
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000)
        navController.popBackStack()
        navController.navigate("main")
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.weather_app_icon),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .align(Alignment.Center)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            CircularProgressBar()
        }
    }

}