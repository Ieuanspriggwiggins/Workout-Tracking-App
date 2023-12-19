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

    /**
     * Check if the database table is empty, if so, then needs populating
     * as the app has been started for the first time (or the database has been
     * deleted through some other process)
     */
    init{
        viewModelScope.launch{
            val testList: List<WorkoutDay> = workoutDayRepository.getWorkouts().first()
            //Assume it is empty, and populate, otherwise continue as normal
            if(testList.isEmpty()){
                workoutDayRepository.populateDefaultValues()
            }
        }
    }
}