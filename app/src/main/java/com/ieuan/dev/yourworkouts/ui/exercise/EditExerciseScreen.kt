package com.ieuan.dev.yourworkouts.ui.exercise

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.TAG
import com.ieuan.dev.yourworkouts.model.ExerciseViewModel
import com.ieuan.dev.yourworkouts.ui.components.AlertDialogComponent
import com.ieuan.dev.yourworkouts.ui.components.ExerciseEditCreateForm
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun EditExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = viewModel()
) {
    val dataState = viewModel.dataState

    var expanded by remember {mutableStateOf(false)}


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
        formTitle = stringResource(R.string.edit_exercise_screen_title),
        onSaveClick = {
            viewModel.checkExerciseSubmission()
            if(!viewModel.isFieldEmptyError){
                viewModel.updateExercise()
                navController.popBackStack()
            }
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
                            Text(text = stringResource(id = R.string.delete_exercise_btn))
                        },
                        onClick = {
                            viewModel.deleteExercise()
                            navController.popBackStack()
                        }
                    )
                }
            }
        }

    ){
        ExerciseEditCreateForm(
            dataState = dataState,
            viewModel = viewModel
        )
    }
}
