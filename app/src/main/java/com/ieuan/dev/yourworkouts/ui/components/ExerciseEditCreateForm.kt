/**
 * @author ieuan sprigg-wiggins
 * Reusable composable component for the form for entry of the exercise information
 */

package com.ieuan.dev.yourworkouts.ui.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.ExerciseViewModel
import com.ieuan.dev.yourworkouts.model.data.ExerciseData
import com.ieuan.dev.yourworkouts.model.data.isValidFloat
import com.ieuan.dev.yourworkouts.model.data.isValidInt


@Composable
fun ExerciseEditCreateForm(
    dataState: ExerciseData,
    viewModel: ExerciseViewModel,

) {
    val imageGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = {
            viewModel.exerciseImageUri.value = it
        } )

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .verticalScroll(rememberScrollState()),
    ){
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

        /**
         * Number of sets entry
         */
        OutlinedTextField(
            label = {
                Text(text = stringResource(R.string.exercise_set_count_input_label))
            },
            value = dataState.numberOfSets,
            onValueChange = {
                if(isValidInt(it) || it.isEmpty()) //
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
            onClick = {
                imageGallery.launch(arrayOf("image/*"))
            },
            modifier = Modifier
                .padding(bottom = 24.dp)
        ) {
            Text(text = stringResource(R.string.upload_img_btn))
        }

        viewModel.exerciseImageUri.value?.let{
            AsyncImage(
                model = viewModel.exerciseImageUri.value,
                contentDescription = null,
            )
        }

        /**
         * Is drop-set enabled
         */

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
                    if(isValidInt(it) || it.isEmpty())
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
                    if(isValidFloat(it) || it.isEmpty())
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
                    if(isValidFloat(it) || it.isEmpty())
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
                    if(isValidFloat(it) || it.isEmpty())
                        viewModel.dataState = dataState.copy(exerciseDropSetWeightTwo = it)
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
                    if(isValidFloat(it) || it.isEmpty())
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