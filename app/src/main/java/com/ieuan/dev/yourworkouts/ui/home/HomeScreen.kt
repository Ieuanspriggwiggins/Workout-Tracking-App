package com.ieuan.dev.yourworkouts.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.model.HomeScreenViewModel
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {

    TopLevelScaffold(
        navController = navController,
        screenTitle = ""
    ) {

    }
}