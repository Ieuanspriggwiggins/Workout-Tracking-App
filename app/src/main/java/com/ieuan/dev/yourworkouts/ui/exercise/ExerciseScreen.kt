package com.ieuan.dev.yourworkouts.ui.exercise

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.ExerciseCard
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import com.ieuan.dev.yourworkouts.ui.navigation.Screen


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
        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .verticalScroll(state = rememberScrollState())
        ){
            exerciseList.forEach{exercise ->
                ExerciseCard(exercise)
            }
        }
    }
}