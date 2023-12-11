package com.ieuan.dev.yourworkouts.ui.exercise

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun CreateExerciseScreen(
    navController: NavController,
    viewModel: CreateExerciseViewModel = viewModel()
) {

    val dataState = viewModel.dataState

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.create_exercise_screen_title),
        onSaveClick = {
            viewModel.submitExercise()
            navController.popBackStack()
        }

    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(rememberScrollState()),
        ){
            /**
             * Exercise name entry
             */
            OutlinedTextField(
                label = {
                        Text(text = stringResource(R.string.exercise_name_input_label))
                },
                value = dataState.exerciseName,
                onValueChange = {
                    viewModel.dataState = dataState.copy(exerciseName = it)
                },
                trailingIcon = {
                    if(dataState.exerciseName.isNotEmpty()){
                        IconButton(
                            onClick = {viewModel.dataState = dataState.copy(exerciseName = "")}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = null
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            /**
             * Number of sets entry
             */
            OutlinedTextField(
                label = {
                    Text(text = stringResource(R.string.exercise_set_count_input_label))
                },
                value = dataState.numberOfSets,
                onValueChange = {
                    if(viewModel.isValidInt(it))
                        viewModel.dataState = dataState.copy(numberOfSets = it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            //TODO: add functionality for image
            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Text(text = stringResource(R.string.upload_img_btn))
            }

            /**
             * Is drop-set enabled
             */
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = stringResource(R.string.is_drop_set_label))
                Switch(
                    checked = dataState.isDropSetEnabled,
                    onCheckedChange = {
                        viewModel.dataState = dataState.copy(isDropSetEnabled = !dataState.isDropSetEnabled)
                    },
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }

            if(!dataState.isDropSetEnabled){
                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.exercise_rep_count_input_label))
                    },
                    value = dataState.numberOfReps,
                    onValueChange = {
                        if(viewModel.isValidInt(it))
                            viewModel.dataState = dataState.copy(numberOfReps = it)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.exercise_weight_label))
                    },
                    value = dataState.exerciseWeight,
                    onValueChange = {
                        if(viewModel.isValidFloat(it))
                            viewModel.dataState = dataState.copy(exerciseWeight = it)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

            }else{
                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.exercise_drop_set_first_weight_label))
                    },
                    value = dataState.exerciseDropSetWeightOne,
                    onValueChange = {
                        if(viewModel.isValidFloat(it))
                            viewModel.dataState = dataState.copy(exerciseDropSetWeightOne = it)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.exercise_drop_set_second_weight_label))
                    },
                    value = dataState.exerciseDropSetWeightTwo,
                    onValueChange = {
                        if(viewModel.isValidFloat(it))
                            viewModel.dataState = dataState.copy(exerciseDropSetWeightThree = it)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    label = {
                        Text(text = stringResource(R.string.exercise_drop_set_third_weight_label))
                    },
                    value = dataState.exerciseDropSetWeightThree,
                    onValueChange = {
                        if(viewModel.isValidFloat(it))
                            viewModel.dataState = dataState.copy(exerciseDropSetWeightThree = it)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )
            }
        }
    }
}