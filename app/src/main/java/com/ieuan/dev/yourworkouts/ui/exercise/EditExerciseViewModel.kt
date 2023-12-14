package com.ieuan.dev.yourworkouts.ui.exercise

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.model.data.Exercise
import com.ieuan.dev.yourworkouts.model.data.ExerciseData
import com.ieuan.dev.yourworkouts.model.data.ExerciseRepository
import com.ieuan.dev.yourworkouts.model.data.dataStateToExerciseEntity
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditExerciseViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
): AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)

    private val exerciseId: Int = checkNotNull(savedStateHandle["exerciseId"]);

    private var exerciseObj: Exercise = Exercise()

    var dataState by mutableStateOf(ExerciseData())

    var exerciseImageUri by mutableStateOf<Uri?>(null)

    init {
        viewModelScope.launch{
            exerciseObj = exerciseRepository.getExercise(exerciseId).filterNotNull().first()
            //Create the state variable used for the edit screen
            dataState = ExerciseData(
                exerciseName = exerciseObj.exerciseName,
                numberOfSets = exerciseObj.numOfSets.toString(),
                numberOfReps = exerciseObj.numOfReps.toString(),
                exerciseWeight = exerciseObj.exerciseWeight.toString(),
                isDropSetEnabled = exerciseObj.isDropSet,
                exerciseDropSetWeightOne = exerciseObj.dropSetFirstWeight.toString(),
                exerciseDropSetWeightTwo = exerciseObj.dropSetSecondWeight.toString(),
                exerciseDropSetWeightThree = exerciseObj.dropSetThirdWeight.toString()
            )
            exerciseImageUri = exerciseObj.exerciseImage.toUri()
        }
    }

    fun updateExercise() {
        val exercise = dataStateToExerciseEntity(dataState, exerciseImageUri)
        exercise.id = exerciseId
        viewModelScope.launch{
            exerciseRepository.update(exercise)
        }
    }

    fun deleteExercise() {
        viewModelScope.launch{
            exerciseRepository.delete(exerciseObj)
        }
    }

}