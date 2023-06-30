# ForecastApp

## Introduction

Simple, small app that uses RestApi to get current weather forecast in Sankt Petersburg

## Technologies used in this project

- Language: Kotlin
- Jetpack Compose
- Coroutines
- Retrofit
- Hilt
- Lottie

 ## Content

 In my app, users can see current weather in Sankt Petersburg, and also by clicking on one of two buttons they can see more detailed forecast or forecast for 5 next days.

 ## How it works
On launch the app displays a splash screen with an animated logo and progress indicator. 
The main screen shows the current temperature in Saint Petersburg, along with an animated image using Lottie that changes based on the weather conditions. 
There are also two buttons to navigate to different screens. The data is retrieved from a free Weather API. 
The Details screen provides more detailed information about the current weather, while the Forecast screen retrieves data using a different method and shows a list of forecast for 5 days every 3 hours.
