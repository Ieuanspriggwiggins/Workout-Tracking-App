package com.ieuan.dev.yourworkouts.ui.exercise

import android.annotation.SuppressLint
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun CreateExerciseScreen(
    navController: NavController,
    viewModel: CreateExerciseViewModel,
) {



    //Get the state variables for the user interface
    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.create_exercise_screen_title),
        onSaveClick = {
            viewModel.createExerciseRecord()
        }

    ) {
        OutlinedTextField(
            value = viewModel.exerciseName.value,
            onValueChange ={
                viewModel.exerciseName.value = it
            }
        )
    }
}