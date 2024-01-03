/**
 * @author ieuan sprigg-wiggins
 * the workout day repository responsible for interacting with the workout day
 * in the database.
 */

package com.ieuan.dev.yourworkouts.datasource

import android.app.Application
import kotlinx.coroutines.flow.Flow

class WorkoutDayRepository(application: Application) {
    private val workoutDayDao = WorkoutRoomDatabase.getDatabase(application)!!.workoutDayDao()

    suspend fun insert(workoutDay: WorkoutDay) = workoutDayDao.insert(workoutDay)

    suspend fun update(workoutDay: WorkoutDay) = workoutDayDao.update(workoutDay)

    suspend fun delete(workoutDay: WorkoutDay) = workoutDayDao.delete(workoutDay)

    fun getWorkout(day: Days): Flow<WorkoutDay> = workoutDayDao.getWorkout(day)

    fun getWorkouts(): Flow<List<WorkoutDay>> = workoutDayDao.getWorkouts()

    fun getNumberOfEnabledWorkouts(): Int = workoutDayDao.getNumberOfEnabledWorkouts()

    suspend fun disableWorkoutDay(workoutDay: String) = workoutDayDao.disableWorkoutDay(workoutDay)

    fun getWorkoutsByState(state: Boolean):
            Flow<List<WorkoutDay>> = workoutDayDao.getWorkoutByState(state)

    /**
     * Uses the update function for workout days iteratively through a given list
     * of workout days
     */
    suspend fun updateFromList(list: List<WorkoutDay>){
        list.forEach{ workout ->
            update(workout)
        }
    }

    /**
     * Populates the database with the default values
     */
    suspend fun populateDefaultValues() {
        val defaultWorkoutsDays = listOf(
            //Monday by default is enabled, as we have to have at least one workout
            WorkoutDay(workoutDay = Days.MONDAY, isEnabled = true),
            WorkoutDay(workoutDay = Days.TUESDAY),
            WorkoutDay(workoutDay = Days.WEDNESDAY),
            WorkoutDay(workoutDay = Days.THURSDAY),
            WorkoutDay(workoutDay = Days.FRIDAY),
            WorkoutDay(workoutDay = Days.SATURDAY),
            WorkoutDay(workoutDay = Days.SUNDAY)
        )

        defaultWorkoutsDays.forEach{workoutDay ->
            insert(workoutDay)
        }
    }
}