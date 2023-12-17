package com.ieuan.dev.yourworkouts.ui.schedule

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ieuan.dev.yourworkouts.model.ScheduleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.TAG
import com.ieuan.dev.yourworkouts.ui.components.DisabledWorkoutCard
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold
import com.ieuan.dev.yourworkouts.ui.navigation.Screen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first


@Composable
fun AddScheduleScreen(
    navController: NavController,
    viewModel: ScheduleViewModel = viewModel()
) {
    val disabledWorkoutsList by viewModel.disabledWorkoutsList.collectAsState(listOf())

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.add_days_to_schedule_title),
        bottomLeftButtonText = stringResource(R.string.add_btn),
        bottomRightButtonText = stringResource(R.string.cancel_btn),
        onSaveClick = {
            viewModel.updateWorkouts(disabledWorkoutsList)
            navController.navigate(Screen.Schedule.route)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 18.dp, start = 18.dp, end = 18.dp)
        ) {
            disabledWorkoutsList.forEach{workout ->
                DisabledWorkoutCard(workout)
            }
        }
    }
}