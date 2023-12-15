package com.ieuan.dev.yourworkouts.model

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import com.ieuan.dev.yourworkouts.model.data.ExerciseData
import com.ieuan.dev.yourworkouts.model.data.dataStateToExerciseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ExerciseViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
): AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)
    val contentResolver = application.contentResolver
    val flags: Int = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION

    var exercises: Flow<List<Exercise>> = exerciseRepository.getExerciseList()
        private set

    var dataState by mutableStateOf(ExerciseData())

    var exerciseImageUri = mutableStateOf<Uri?>(null)


    /**
     * For submitting a new exercise on the create exercise screen
     */
    fun submitExercise() {
        exerciseImageUri.value?.let{contentResolver.takePersistableUriPermission(it, flags)}

        viewModelScope.launch {
            exerciseRepository.insert(dataStateToExerciseEntity(dataState, exerciseImageUri.value))
        }
    }

    val exerciseId: Int? = savedStateHandle["exerciseId"]


    /**
     * Specifically for the update exercise screen, will populate the state data
     * with the exercise data from the database.
     */
    fun populateExerciseData() {
        exerciseId?.let{
            viewModelScope.launch {
                val exerciseObj = exerciseRepository.getExercise(exerciseId)
                    .filterNotNull().first()
                //Set the data state data to the exercise being edited
                dataState.exerciseName = exerciseObj.exerciseName
                dataState.numberOfSets = exerciseObj.numOfSets.toString()
                dataState.numberOfReps = exerciseObj.numOfReps.toString()
                dataState.exerciseWeight = exerciseObj.exerciseWeight.toString()
                dataState.isDropSetEnabled = exerciseObj.isDropSet
                dataState.exerciseDropSetWeightOne = exerciseObj.dropSetFirstWeight.toString()
                dataState.exerciseDropSetWeightTwo = exerciseObj.dropSetSecondWeight.toString()
                dataState.exerciseDropSetWeightThree = exerciseObj.dropSetThirdWeight.toString()
                exerciseImageUri.value = exerciseObj.exerciseImage.toUri()

            }
        }
    }

    fun updateExercise() {

    }

    /**
     * For deleting an exercise on the edit exercise screen
     */
    fun deleteExercise() {
        viewModelScope.launch{
            exerciseId?.let{
                val exerciseObj =  exerciseRepository.getExercise(exerciseId)
                    .filterNotNull().first()
                exerciseRepository.delete(exerciseObj)
            }
        }
    }
}
