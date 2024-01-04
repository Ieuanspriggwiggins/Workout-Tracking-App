/**
 * @author ieuan sprigg-wiggins
 * Composable for the edit a workout day screen, changing the workout name, length and
 * adding exercises to the workout day
 */

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.model.EditWorkoutViewModel
import com.ieuan.dev.yourworkouts.ui.components.AlertDialogComponent
import com.ieuan.dev.yourworkouts.ui.components.ExerciseCard
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun EditWorkoutDayScreen(
    navController: NavController,
    viewModel: EditWorkoutViewModel = viewModel()
) {

    var alertDialogOpen by remember { mutableStateOf(false)}

    val exerciseList by viewModel.exerciseList.collectAsState(listOf())

    var expanded by remember { mutableStateOf(false)}

    if(alertDialogOpen){
        AlertDialogComponent(
        title = stringResource(R.string.warning_disable_schedule_title),
        content = stringResource(R.string.warning_disable_schedule_body),
        onDismissRequest = { alertDialogOpen = false },
        confirmButton = {
            TextButton(onClick = { alertDialogOpen = false }) {
                Text(text = stringResource(R.string.exercise_schedule_dialog_dismiss))
            }
        }
        )
    }

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.edit_workout_title),
        hasFabIcon = true,
        fabOnclick = {
            val workoutDay = viewModel.workoutDay
            navController.navigate("AddExerciseToScheduleScreen/$workoutDay")
        },
        onSaveClick = {
            viewModel.updateWorkoutDay()
            navController.popBackStack()
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
                            if(viewModel.isAllowedToDisable()){
                                viewModel.disableWorkoutDay()
                                navController.popBackStack()
                            }
                            else{
                                alertDialogOpen = true
                                expanded = false
                            }
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScheduleCard(
    exercise: Exercise,
    viewModel: EditWorkoutViewModel
) {
    var expanded by remember {mutableStateOf(false)}
    var dialogOpened by remember {mutableStateOf(false)}

    if(dialogOpened){
        Dialog(onDismissRequest = {dialogOpened = false}) {
            ExerciseCard(exercise = exercise)
        }
    }

    Card(
        shape = RoundedCornerShape(0),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 4.dp),
        onClick = {
            dialogOpened = true
        }

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