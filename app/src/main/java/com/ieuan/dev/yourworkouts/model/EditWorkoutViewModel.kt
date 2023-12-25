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
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import kotlinx.coroutines.launch

class EditWorkoutViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val workoutRepository: WorkoutDayRepository = WorkoutDayRepository(application)
    private val exerciseScheduleLinkRepository = ExerciseScheduleLinkRepository(application)
    private val workoutDayRepository = WorkoutDayRepository(application)

    val workoutDay: String = savedStateHandle["workoutDay"]!!

    var workoutObject = workoutRepository.getWorkout(Days.valueOf(workoutDay))

    var workoutName by mutableStateOf("")
    var workoutLength by mutableStateOf("")

    //List of the exercises that are added to the currently editing day/workout
    val exerciseList = exerciseScheduleLinkRepository.getExercisesInSchedule(workoutDay)

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