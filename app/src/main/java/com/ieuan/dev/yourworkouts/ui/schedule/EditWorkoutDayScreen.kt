package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.model.EditWorkoutViewModel
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun EditWorkoutDayScreen(
    navController: NavController,
    viewModel: EditWorkoutViewModel = viewModel()
) {

    val workoutObject by viewModel.workoutObject.collectAsState(WorkoutDay())

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.edit_workout_title),
        hasFabIcon = true,
        fabOnclick = {
            val workoutDay = viewModel.workoutDay
            navController.navigate("AddExerciseToScheduleScreen/$workoutDay")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        ){
            OutlinedTextField(
                label = {
                  Text(text = stringResource(R.string.workout_name_label))
                },
                value = viewModel.workoutName,
                onValueChange = {
                    viewModel.workoutName = it
                },
                supportingText = {
                    var workoutName = "None"
                    if(workoutObject.workoutName.isNotEmpty()){
                        workoutName = workoutObject.workoutName
                    }

                    Text(text = stringResource(R.string.currently_support_text)
                            + ":" + workoutName)
                },
                trailingIcon = {
                    if(viewModel.workoutName.isNotEmpty()){
                        IconButton(onClick = { viewModel.workoutName = "" }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = null
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
            )
            OutlinedTextField(
                label = {
                    Text(text = stringResource(R.string.workout_length_label))
                },
                value = viewModel.workoutLength,
                onValueChange = {
                    viewModel.workoutLength = it
                },
                supportingText = {
                    var workoutName = "None"
                    if(workoutObject.workoutLength.isNotEmpty()){
                        workoutName = workoutObject.workoutLength
                    }

                    Text(text = stringResource(R.string.currently_support_text)
                            + ":" + workoutName)
                },
                trailingIcon = {
                    if(viewModel.workoutLength.isNotEmpty()){
                        IconButton(onClick = { viewModel.workoutLength = "" }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = null
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}