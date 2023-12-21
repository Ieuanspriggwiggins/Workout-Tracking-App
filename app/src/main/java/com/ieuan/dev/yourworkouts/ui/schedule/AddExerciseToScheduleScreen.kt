package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.model.AddExerciseToScheduleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold


@Composable
fun AddExerciseToScheduleScreen(
    navController: NavController,
    viewModel: AddExerciseToScheduleViewModel = viewModel()
){
    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.add_exercise_workout_title)
    ) {

    }
}
