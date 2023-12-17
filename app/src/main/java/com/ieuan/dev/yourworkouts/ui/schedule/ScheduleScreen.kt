package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.ScheduleViewModel
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScheduleScreen(
    navController: NavController,
    viewModel: ScheduleViewModel = viewModel()
) {
    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(id = R.string.schedule_screen_title),
        hasFabIcon = true,
        fabOnclick = {
            navController.navigate("addScheduleScreen")
        }
    ) {

    }
}