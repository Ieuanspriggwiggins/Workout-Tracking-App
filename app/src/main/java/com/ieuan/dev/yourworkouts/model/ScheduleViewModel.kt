package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import com.ieuan.dev.yourworkouts.datasource.Workout
import com.ieuan.dev.yourworkouts.datasource.WorkoutRepository
import kotlinx.coroutines.flow.Flow
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