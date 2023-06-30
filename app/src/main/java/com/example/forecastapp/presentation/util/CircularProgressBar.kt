package com.example.forecastapp.presentation.util

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = Modifier.size(100.dp),
        color = Color.White,
        strokeWidth = 7.dp
    )
}