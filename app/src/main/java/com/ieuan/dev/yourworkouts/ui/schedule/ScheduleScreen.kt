package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.ScheduleScreenViewModel
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScheduleScreen(
    navController: NavController,
    viewModel: ScheduleScreenViewModel = viewModel()
) {
    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(id = R.string.schedule_screen_title),
        hasFabIcon = true,
        fabOnclick = {
            navController.navigate(route = "addWorkoutDayScreen")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 32.dp, start = 8.dp, end = 8.dp)
        ) {

        }
    }
}