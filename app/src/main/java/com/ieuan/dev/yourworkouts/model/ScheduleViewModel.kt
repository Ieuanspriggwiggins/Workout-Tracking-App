package com.ieuan.dev.yourworkouts.model

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.TAG
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import com.ieuan.dev.yourworkouts.datasource.Workout
import com.ieuan.dev.yourworkouts.datasource.WorkoutRepository
import com.ieuan.dev.yourworkouts.model.data.WorkoutEditData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ScheduleViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) :AndroidViewModel(application) {
    private val exerciseRepository = ExerciseRepository(application)
    private val workoutRepository = WorkoutRepository(application)

    //Enabled and disabled schedules/workouts in the app
    val enabledWorkoutsList: Flow<List<Workout>> = workoutRepository.getWorkoutsByState(true)
    val disabledWorkoutsList: Flow<List<Workout>> = workoutRepository.getWorkoutsByState(false)

    private val workoutId: Int? = savedStateHandle["workoutId"]

    var dataState: WorkoutEditData by mutableStateOf(WorkoutEditData())

    /**
     * Updates the workouts list in the database with any changes made
     */
    fun updateWorkouts(list: List<Workout>) {
        viewModelScope.launch{
            workoutRepository.updateWorkouts(list)
        }
    }

    /**
     * Check if schedules database table is empty, if so, populate
     */
    init {
        //If the table is empty, populate with the default values
        if(workoutRepository.isEmpty()){
            viewModelScope.launch {
                workoutRepository.insertDefaultListOfWorkouts()
            }
        }
    }

}