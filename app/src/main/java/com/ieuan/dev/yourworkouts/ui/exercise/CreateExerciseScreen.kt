package com.ieuan.dev.yourworkouts.ui.exercise

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.ExerciseViewModel
import com.ieuan.dev.yourworkouts.model.data.ExerciseData
import com.ieuan.dev.yourworkouts.model.data.checkExerciseSubmission
import com.ieuan.dev.yourworkouts.model.data.isValidFloat
import com.ieuan.dev.yourworkouts.model.data.isValidInt
import com.ieuan.dev.yourworkouts.ui.components.AlertDialogComponent
import com.ieuan.dev.yourworkouts.ui.components.ExerciseEditCreateForm
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold


@Composable
fun CreateExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = viewModel()
) {
    val dataState by remember {mutableStateOf<ExerciseData>(viewModel.dataState)}

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.create_exercise_screen_title),
        onSaveClick = {
            viewModel.submitExercise()
            navController.popBackStack()
        }
    ) {
        ExerciseEditCreateForm(
            dataState = dataState,
            viewModel = viewModel
        )
    }
}