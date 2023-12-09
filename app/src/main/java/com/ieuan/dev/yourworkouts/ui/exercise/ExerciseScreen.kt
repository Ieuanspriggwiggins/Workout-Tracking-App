package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import com.ieuan.dev.yourworkouts.ui.navigation.Screen

@Composable
fun ExerciseScreen(
    navController: NavController
) {
    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(id = R.string.exercise_screen_title),
        hasFabIcon = true,
        fabOnclick = {
            navController.navigate(Screen.CreateExercise.route)
        }
    ) {

    }
}