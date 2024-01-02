package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ScheduleScreenViewModel(application: Application) :AndroidViewModel(application)
{
    //get the workout day repository
    private val workoutDayRepository = WorkoutDayRepository(application)

    //List of days/workouts on the schedule screen
    val enabledDayList = workoutDayRepository.getWorkoutsByState(true)
}