package com.example.forecastapp.presentation.main_screen.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreenTopBar(city: String) {

    TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = city,
                color = Color.White,
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}