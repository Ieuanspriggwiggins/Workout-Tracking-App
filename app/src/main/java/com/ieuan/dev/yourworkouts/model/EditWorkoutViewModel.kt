/**
 * @author ieuan sprigg-wiggins
 * View model class for the screen for editing a workout day (changing the name
 * or the length of the workout)
 */

package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.datasource.Days
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.datasource.ExerciseScheduleLinkRepository
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditWorkoutViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val exerciseScheduleLinkRepository = ExerciseScheduleLinkRepository(application)
    private val workoutDayRepository = WorkoutDayRepository(application)

    //The workout day as a string (i.e. 'THURSDAY'), specified as not null because
    //the screen cannot be called without this information
    val workoutDay: String = savedStateHandle["workoutDay"]!!

    //A source of truth for the workout name and the length used by the UI, handles state
    var workoutName by mutableStateOf("")
    var workoutLength by mutableStateOf("")

    //List of the exercises that are added to the currently editing day/workout
    val exerciseList = exerciseScheduleLinkRepository.getExercisesInSchedule(workoutDay)

    //The workout day object for getting the existing values from the database for
    //the specified workout day
    private var workoutDayObj: WorkoutDay? = null

    /**
     * When the view model is created, populate the mutable values with the current existing
     * for the object.
     */
    init {
        viewModelScope.launch {
            workoutDayObj = workoutDayRepository.getWorkout(Days.valueOf(workoutDay)).first()
            workoutDayObj?.let {
                workoutName = workoutDayObj!!.workoutName
                workoutLength = workoutDayObj!!.workoutLength
            }
        }
    }

    /**
     * Updates the workout day with the entered name and workout length
     */
    fun updateWorkoutDay() {
        viewModelScope.launch{
            val workoutDay = WorkoutDay(
                workoutName = workoutName,
                workoutLength = workoutLength,
                isEnabled = true,
                workoutDay = Days.valueOf(workoutDay)
            )
            workoutDayRepository.update(workoutDay)
        }
    }

    /**
     * Remove exercise from the schedule
     */
    fun removeExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseScheduleLinkRepository.deleteExerciseByDayAndId(exercise.id, workoutDay)
        }
    }

    /**
     * Returns true if allowed to disable the workout day, false if not
     * Can't disable if there is only one currently enabled workout day
     */
    fun isAllowedToDisable(): Boolean{
        return workoutDayRepository.getNumberOfEnabledWorkouts() > 1
    }

    /**
     * Disables the workout day in the database and removes any links to exercises
     */
    fun disableWorkoutDay() {
        viewModelScope.launch {
            workoutDayRepository.disableWorkoutDay(workoutDay)
            exerciseScheduleLinkRepository.deleteExercisesForWorkoutDay(workoutDay)

        }
    }
}