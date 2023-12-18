package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.ScheduleViewModel
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun EditWorkoutScreen(
    navController: NavController,
    viewModel: ScheduleViewModel = viewModel()
) {
    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.edit_workout_title)
    ) {

    }
}