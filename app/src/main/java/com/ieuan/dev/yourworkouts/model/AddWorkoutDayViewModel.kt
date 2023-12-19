package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import com.ieuan.dev.yourworkouts.datasource.WorkoutRoomDatabase

class AddWorkoutDayViewModel(
    application: Application
) : AndroidViewModel(application){
    val workoutDayRepository = WorkoutDayRepository(application)
}