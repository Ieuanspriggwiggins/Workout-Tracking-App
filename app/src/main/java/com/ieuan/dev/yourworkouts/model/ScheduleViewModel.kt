package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository

class ScheduleViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) :AndroidViewModel(application) {
    val exerciseRepository = ExerciseRepository(application)


}