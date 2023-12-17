package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import com.ieuan.dev.yourworkouts.datasource.WorkoutRepository
import kotlinx.coroutines.flow.forEach

class ScheduleViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) :AndroidViewModel(application) {
    val exerciseRepository = ExerciseRepository(application)
    val workoutRepository = WorkoutRepository(application)

    /**
     * Check if the database has the data already for the schedules that can be
     * added/enabled for the user. If not, create them
     */


}