package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.model.data.Exercise
import com.ieuan.dev.yourworkouts.model.data.ExerciseDao
import kotlinx.coroutines.launch


class CreateExerciseViewModel(private val exerciseDao: ExerciseDao) : ViewModel() {

    var exerciseName = mutableStateOf("")
    var numberOfSets = mutableStateOf("")
    var numberOfReps = mutableStateOf("")
    var exerciseWeight = mutableStateOf("")
    var isDropSetEnabled = mutableStateOf(false)
    var exerciseDropSetWeightOne = mutableStateOf("")
    var exerciseDropSetWeightTwo = mutableStateOf("")
    var exerciseDropSetWeightThree = mutableStateOf("")
    var exerciseImageUri = mutableStateOf("")

    fun createExerciseRecord() {
        viewModelScope.launch{
            exerciseDao.insert(Exercise(exerciseName = "Test"))
        }
    }

    //Checks if the value parsed is a valid float from string
    fun checkValidFloat(s: String): Boolean {
        return s.toFloatOrNull() !== null
    }

    fun checkValidInt(s: String): Boolean {
        return s.toIntOrNull() !== null
    }

}
