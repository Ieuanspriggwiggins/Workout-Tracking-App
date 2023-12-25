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

    @Query("SELECT * FROM workout_day WHERE workoutDay = :day")
    fun getWorkout(day: Days): Flow<WorkoutDay>

    @Query("SELECT * FROM workout_day")
    fun getWorkouts(): Flow<List<WorkoutDay>>

    @Query("SELECT * FROM workout_day WHERE isEnabled = :state")
    fun getWorkoutByState(state: Boolean): Flow<List<WorkoutDay>>

    @Query("SELECT COUNT(*) FROM workout_day WHERE isEnabled = 1")
    fun getNumberOfEnabledWorkouts(): Int

    @Query("UPDATE workout_day SET isEnabled = 0 WHERE workoutDay = :workoutDay")
    suspend fun disableWorkoutDay(workoutDay: String)
}