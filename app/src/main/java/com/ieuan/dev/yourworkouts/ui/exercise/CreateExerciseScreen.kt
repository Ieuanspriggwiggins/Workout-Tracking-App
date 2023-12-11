package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.ViewModelProvider
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun CreateExerciseScreen(
    navController: NavController,
    viewModel: CreateExerciseViewModel = viewModel(factory = ViewModelProvider.Factory)
) {

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.create_exercise_screen_title),
        onSaveClick = {
            viewModel.createExerciseRecord()
        }

    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(rememberScrollState()),
        ){
            OutlinedTextField(
                value = viewModel.exerciseData.exerciseName,
                onValueChange = {value ->
                    viewModel.exerciseData = viewModel.exerciseData.copy(value)
                }
            )
        }
    }
}