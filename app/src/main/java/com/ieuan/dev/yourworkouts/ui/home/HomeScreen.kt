package com.ieuan.dev.yourworkouts.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {

    //Generate the title for the home screen
    val calendar = Calendar.getInstance()
    val date = calendar.time
    val currentDay = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
    val screenTitle = "Your Workout: $currentDay"

    TopLevelScaffold(
        navController = navController,
        screenTitle = screenTitle
    ) {

    }
}