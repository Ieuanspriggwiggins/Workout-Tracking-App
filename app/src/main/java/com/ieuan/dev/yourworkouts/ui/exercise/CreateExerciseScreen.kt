/**
 * @author ieuan sprigg-wiggins
 * The screen for creating an exercise and adding it to the application
 */

package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.ExerciseViewModel
import com.ieuan.dev.yourworkouts.ui.components.AlertDialogComponent
import com.ieuan.dev.yourworkouts.ui.components.ExerciseEditCreateForm
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold


@Composable
fun CreateExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = viewModel()
) {
    val dataState = viewModel.dataState
    //if the currently added values are not correct, display the alert dialog specifying
    //that the user input is invalid
    if(viewModel.isFieldEmptyError){
        AlertDialogComponent(
            title = stringResource(id = R.string.exercise_dialog_error_title),
            content = stringResource(id = R.string.exercise_dialog_error_text),
            onDismissRequest = { viewModel.isFieldEmptyError = false }
        ) {
            TextButton(
                onClick = { viewModel.isFieldEmptyError = false }
            ) {
                Text(text = stringResource(id = R.string.exercise_dialog_error_button_text))
            }
        }
    }

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.create_exercise_screen_title),
        onSaveClick = {
            viewModel.checkExerciseSubmission()
            if(!viewModel.isFieldEmptyError){
                viewModel.submitExercise()
                navController.popBackStack()
            }
        }
    ) {
        ExerciseEditCreateForm(
            dataState = dataState,
            viewModel = viewModel
        )
    }
}