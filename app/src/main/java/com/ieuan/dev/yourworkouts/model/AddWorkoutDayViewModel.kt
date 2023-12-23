package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AddWorkoutDayViewModel(
    application: Application
) : AndroidViewModel(application){
    private val workoutDayRepository = WorkoutDayRepository(application)

    //Get the list of days that aren't current enabled
    var disabledDayList: Flow<List<WorkoutDay>> = workoutDayRepository.getWorkoutsByState(false)
        private set

    fun updateList(list: List<WorkoutDay>) {
        viewModelScope.launch {
            workoutDayRepository.updateFromList(list)
        }
    }
}