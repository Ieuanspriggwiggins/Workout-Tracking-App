package com.ieuan.dev.yourworkouts.ui.schedule

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.model.AddExerciseToScheduleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.TAG
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseToScheduleScreen(
    navController: NavController,
    viewModel: AddExerciseToScheduleViewModel = viewModel()
){
    val list by viewModel.exerciseList.collectAsState(listOf())

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.add_exercise_workout_title),
        onSaveClick = {
            viewModel.addExercisesToSchedule()
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp)
        ) {
            list.forEach{exercise ->
                var checked by remember {mutableStateOf(false)}

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(0),
                    onClick = {

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
