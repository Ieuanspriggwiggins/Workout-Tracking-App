/**
 * @author ieuan sprigg-wiggins
 *
 *
 */

package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.datasource.Days
import com.ieuan.dev.yourworkouts.datasource.ExerciseScheduleLink
import com.ieuan.dev.yourworkouts.datasource.ExerciseScheduleLinkRepository
import kotlinx.coroutines.launch

class AddExerciseToScheduleViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val exerciseScheduleLinkRepository = ExerciseScheduleLinkRepository(application)

    val workoutDay: String = savedStateHandle["workoutDay"]!!

    val setOfExercises = mutableSetOf<Int>()

    fun addExercisesToSchedule() {
        viewModelScope.launch {
            setOfExercises.forEach{ exercise ->
                val link = ExerciseScheduleLink(Days.valueOf(workoutDay), exercise)
                exerciseScheduleLinkRepository.insert(link)
            }
        }
    }

    val exerciseList = exerciseScheduleLinkRepository.getExercisesNotInSchedule(workoutDay)
}