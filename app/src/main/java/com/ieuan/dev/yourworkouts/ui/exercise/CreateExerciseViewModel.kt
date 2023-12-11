package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.model.data.Exercise
import com.ieuan.dev.yourworkouts.model.data.ExerciseDao
import kotlinx.coroutines.launch


class CreateExerciseViewModel(
    private val exerciseDao: ExerciseDao) : ViewModel() {

    var exerciseData by mutableStateOf(ExerciseData())

    //Checks if the value parsed is a valid float from string
    fun checkValidFloat(s: String): Boolean {
        return s.toFloatOrNull() !== null
    }

    fun checkValidInt(s: String): Boolean {
        return s.toIntOrNull() !== null
    }

    fun createExerciseRecord(){
        viewModelScope.launch {
            exerciseDao.insert(Exercise(exerciseName = exerciseData.exerciseName))
        }
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
    var exerciseImageUri: String = "",
)
