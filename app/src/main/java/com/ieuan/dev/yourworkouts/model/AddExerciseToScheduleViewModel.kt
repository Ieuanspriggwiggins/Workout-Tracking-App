package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository

class AddExerciseToScheduleViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val exerciseRepository = ExerciseRepository(application)

    val workoutDay: String = savedStateHandle["workoutDay"]!!

    //TODO: Make get exercises that are not in this schedule
    val exerciseList = exerciseRepository.getExerciseList()
}