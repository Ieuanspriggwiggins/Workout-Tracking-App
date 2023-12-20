package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.ieuan.dev.yourworkouts.datasource.Days
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository

class EditWorkoutViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val workoutRepository: WorkoutDayRepository = WorkoutDayRepository(application)

    private val workoutDay: String = savedStateHandle["workoutDay"]!!

    var workoutObject = workoutRepository.getWorkout(Days.valueOf(workoutDay))

    var workoutName by mutableStateOf("")
    var workoutLength by mutableStateOf("")
}