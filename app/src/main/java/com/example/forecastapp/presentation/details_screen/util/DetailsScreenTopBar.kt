package com.example.forecastapp.presentation.details_screen.util

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forecastapp.R

@Composable
fun DetailsScreenTopBar(
    city: String, onBackButtonClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = city,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackButtonClick,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}
