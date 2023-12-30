package com.ieuan.dev.yourworkouts.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.model.HomeScreenViewModel
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {

    val exerciseList by viewModel.exerciseList.collectAsState(listOf())

    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.home_screen_title) + viewModel.currentDayString
    ) {
        exerciseList.forEach{exercise ->
            //TODO: Make exercises show on the homepage
        }
    }
}