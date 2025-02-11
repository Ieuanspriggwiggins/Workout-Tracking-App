/**
 * @author ieuan sprigg-wiggins
 * Composable for screen to add an exercise to a workout day
 */

package com.ieuan.dev.yourworkouts.ui.schedule

import android.util.Log
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.model.AddExerciseToScheduleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.TAG
import com.ieuan.dev.yourworkouts.ui.components.ExerciseCard
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseToScheduleScreen(
    navController: NavController,
    viewModel: AddExerciseToScheduleViewModel = viewModel()
){
    //List of exercises that don't exist in the current workout day
    val list by viewModel.exerciseList.collectAsState(listOf())

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.add_exercise_workout_title),
        onSaveClick = {
            viewModel.addExercisesToSchedule()
            navController.popBackStack()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp)
        ){
            items(list) { exercise ->
                var checked by remember {mutableStateOf(false)}
                var isDialogOpened by remember {mutableStateOf(false)}

                if(isDialogOpened){
                    Dialog(onDismissRequest = {isDialogOpened = !isDialogOpened}) {
                        ExerciseCard(exercise = exercise)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(0),
                    onClick = {
                        isDialogOpened = true
                    }

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, bottom = 5.dp, top = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = exercise.exerciseName)
                        Checkbox(
                            checked = checked ,
                            onCheckedChange = {
                                checked = !checked
                                if(checked){
                                    viewModel.setOfExercises.add(exercise.id)
                                }else{
                                    viewModel.setOfExercises.remove(exercise.id)
                                }
                                Log.i(TAG, viewModel.setOfExercises.toString())
                            }
                        )
                    }
                }
            }
        }
    }
}
