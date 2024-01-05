/**
 * @author ieuan sprigg-wiggins
 * Composable for top level home screen on the application, displays the exercises
 * the user has added to the workout day for that specific day. Also displays the workout
 * name and length if the user has specified such.
 */

package com.ieuan.dev.yourworkouts.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.model.HomeScreenViewModel
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.ui.components.ExerciseCard

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {

    val exerciseList by viewModel.exerciseList.collectAsState(listOf())

    val workoutDay by viewModel.workoutDay.collectAsState(WorkoutDay())

    var title: String = viewModel.currentDayString

    //If the user has set a workout name for the workout, replace the default title
    //for the day and show the workout name instead.
    if(workoutDay.workoutName.isNotEmpty()) {
        title = workoutDay.workoutName
    }

    TopLevelScaffold(
        navController = navController,
        screenTitle = title
    ) {
        if(workoutDay.workoutLength.isNotEmpty()){
            Text(
                text = workoutDay.workoutLength,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 24.sp
            )
        }
        //If the exercise list is empty, show a prompt saying no exercises have been assigned to this workout day
        if(exerciseList.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(R.string.warning_no_workout_homescreen),
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(top = 24.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
        ){
            items(exerciseList) {exercise ->
                ExerciseHomeScreenCard(exercise = exercise)
            }
        }
    }
}

@Composable
fun ExerciseHomeScreenCard(exercise: Exercise) {
    //If the exercise is a drop set, show the amount of times that drop set
    //exercise needs to be repeated.
    if(exercise.isDropSet){
        Text(
            text = stringResource(R.string.exercise_drop_set_homescreen_repeat) + ": " + exercise.numOfSets.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.drop_set_rest_set),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            fontSize = 16.sp
        )
        ExerciseCard(
            exercise = exercise,
            extraContent = {
                Text(
                    text = stringResource(R.string.drop_set_rest_rep),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        )
    }else{
        ExerciseCard(
            exercise = exercise,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}