package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import com.ieuan.dev.yourworkouts.ui.navigation.Screen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList

@Composable
fun ExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = viewModel()
) {

    val exerciseList by viewModel.exercises.collectAsState(listOf())

    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(id = R.string.exercise_screen_title),
        hasFabIcon = true,
        fabOnclick = {
            navController.navigate(Screen.CreateExercise.route)
        }
    ) {
        Column{
            exerciseList.forEach{exercise ->
                Card{
                    Text(text = exercise.exerciseName)
                }
            }
        }
    }
}