package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.model.EditWorkoutViewModel
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun EditWorkoutDayScreen(
    navController: NavController,
    viewModel: EditWorkoutViewModel = viewModel()
) {

    val workoutObject by viewModel.workoutObject.collectAsState(WorkoutDay())
    val exerciseList by viewModel.exerciseList.collectAsState(listOf())

    var expanded by remember { mutableStateOf(false)}

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.edit_workout_title),
        hasFabIcon = true,
        fabOnclick = {
            val workoutDay = viewModel.workoutDay
            navController.navigate("AddExerciseToScheduleScreen/$workoutDay")
        },
        actions = {
            Box{
                IconButton(
                    onClick = {
                        expanded = !expanded
                    }
                ) {
                    Icon(
                        imageVector = (Icons.Filled.MoreVert),
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(id = R.string.disable_workout_day_btn))
                        },
                        onClick = {
                            viewModel.disableWorkoutDay()
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
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
            exerciseList.forEach{exercise ->
                ExerciseScheduleCard(exercise, viewModel)
            }
        }
    }
}
@Composable
fun ExerciseScheduleCard(
    exercise: Exercise,
    viewModel: EditWorkoutViewModel
) {
    var expanded by remember {mutableStateOf(false)}

    Card(
        shape = RoundedCornerShape(0),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 4.dp),

    ){
        Row(
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = exercise.exerciseName)
            Box{
                IconButton(
                    onClick = {
                        expanded = !expanded
                    }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.remove_exercise_btn)) },
                        onClick = {
                            viewModel.removeExercise(exercise)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}