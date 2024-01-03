/**
 * @author ieuan sprigg-wiggins
 *
 * Interface for the data access object for the workout days in the application
 */

package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDayDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workoutDay: WorkoutDay)

    @Update
    suspend fun update(workoutDay: WorkoutDay)

    @Delete
    suspend fun delete(workoutDay: WorkoutDay)

    /**
     *  Returns a single workout where the passed day is equal to a record
     */
    @Query("SELECT * FROM workout_day WHERE workoutDay = :day")
    fun getWorkout(day: Days): Flow<WorkoutDay>

    /**
     * Returns all workout days in a list
     */
    @Query("SELECT * FROM workout_day")
    fun getWorkouts(): Flow<List<WorkoutDay>>

    /**
     * Returns all workouts by state (whether they are enabled or not)
     */
    @Query("SELECT * FROM workout_day WHERE isEnabled = :state")
    fun getWorkoutByState(state: Boolean): Flow<List<WorkoutDay>>

    /**
     * Returns the number of enabled workouts as an integer
     */
    @Query("SELECT COUNT(*) FROM workout_day WHERE isEnabled = 1")
    fun getNumberOfEnabledWorkouts(): Int

    /**
     * Updates a workout to a disabled state by the workout day that is passed as
     * an argument into the function
     */
    @Query("UPDATE workout_day SET isEnabled = 0 WHERE workoutDay = :workoutDay")
    suspend fun disableWorkoutDay(workoutDay: String)
}