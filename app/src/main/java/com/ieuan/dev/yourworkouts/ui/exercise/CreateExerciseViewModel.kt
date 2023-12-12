package com.ieuan.dev.yourworkouts.ui.exercise

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.model.data.Exercise
import com.ieuan.dev.yourworkouts.model.data.ExerciseRepository
import kotlinx.coroutines.launch


class CreateExerciseViewModel(application: Application): AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)

    var dataState by mutableStateOf(ExerciseData())

    //Store the image uri for the image given by the user
    var exerciseImageUri = mutableStateOf<Uri?>(null)

    /**
     * Submits the exercises to the database
     */
    fun submitExercise() {

        viewModelScope.launch{
            exerciseRepository.insert(dataStateToExerciseEntity(dataState))
        }
    }

    /**
     * Converts the data state data object into a valid exercise entity for database entry
     * checks if the values are transferable to the given type, otherwise app would crash if a value
     * is empty when creating the entity
     */
    private fun dataStateToExerciseEntity(exerciseData: ExerciseData): Exercise {
        //Validate the data in dataState
        val defaultIntValue = 0
        val defaultFloatValue = 0f
        return Exercise(
            exerciseName = exerciseData.exerciseName,
            numOfSets = exerciseData.numberOfSets.toIntOrNull() ?: defaultIntValue,
            numOfReps = exerciseData.numberOfReps.toIntOrNull() ?: defaultIntValue,
            exerciseWeight = exerciseData.exerciseWeight.toFloatOrNull() ?: defaultFloatValue,
            isDropSet = exerciseData.isDropSetEnabled,
            dropSetFirstWeight = exerciseData.exerciseDropSetWeightOne.toFloatOrNull() ?: defaultFloatValue,
            dropSetSecondWeight = exerciseData.exerciseDropSetWeightTwo.toFloatOrNull() ?: defaultFloatValue,
            dropSetThirdWeight = exerciseData.exerciseDropSetWeightThree.toFloatOrNull() ?: defaultFloatValue,
            exerciseImage = exerciseImageUri.value.toString()
        )

    }

    fun isValidInt(s: String): Boolean {
        return s.toIntOrNull() != null
    }

    fun isValidFloat(s: String): Boolean {
        return s.toFloatOrNull() != null
    }

}

data class ExerciseData(
    var exerciseName: String = "",
    var numberOfSets: String = "",
    var numberOfReps: String = "",
    var exerciseWeight: String = "",
    var isDropSetEnabled: Boolean = false,
    var exerciseDropSetWeightOne: String = "",
    var exerciseDropSetWeightTwo: String = "",
    var exerciseDropSetWeightThree: String = "",
)
