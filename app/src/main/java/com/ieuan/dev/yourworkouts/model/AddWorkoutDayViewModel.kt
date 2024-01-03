/**
 * View model class responsible for the screen for enabling a workout day in the
 * application
 */

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
    //Get the required repository for necessary actions in the database
    private val workoutDayRepository = WorkoutDayRepository(application)

    //Get the list of days that aren't current enabled
    var disabledDayList: Flow<List<WorkoutDay>> = workoutDayRepository.getWorkoutsByState(false)
        private set

    //Updates the list with the list that the user has selected
    fun updateList(list: List<WorkoutDay>) {
        viewModelScope.launch {
            workoutDayRepository.updateFromList(list)
        }
    }
}