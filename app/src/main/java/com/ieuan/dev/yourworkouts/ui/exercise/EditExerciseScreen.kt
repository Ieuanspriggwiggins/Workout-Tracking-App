package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun EditExerciseScreen(
    navController: NavController,
    viewModel: EditExerciseViewModel = viewModel()
) {
    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.edit_exercise_screen_title)
    ) {
        val imageGallery = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = {
                viewModel.exerciseImageUri.value = it
            }
        )
    }
}