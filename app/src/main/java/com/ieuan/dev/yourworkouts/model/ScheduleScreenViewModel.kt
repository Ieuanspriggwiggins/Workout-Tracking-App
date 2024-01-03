/**
 * @author ieuan sprigg-wiggins
 * View model class for the schedule screen (top level screen)
 */
package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository

class ScheduleScreenViewModel(application: Application) :AndroidViewModel(application)
{
    //get the workout day repository
    private val workoutDayRepository = WorkoutDayRepository(application)

    //List of days/workouts on the schedule screen
    val enabledDayList = workoutDayRepository.getWorkoutsByState(true)
}