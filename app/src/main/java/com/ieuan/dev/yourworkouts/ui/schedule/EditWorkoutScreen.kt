package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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

    val dataState = viewModel.dataState

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.edit_workout_title)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                label = {
                    Text(text = stringResource(R.string.workout_name_label))
                },
                onValueChange = {
                    viewModel.dataState = dataState.copy(workoutName = it)
                },
                value = dataState.workoutName,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}