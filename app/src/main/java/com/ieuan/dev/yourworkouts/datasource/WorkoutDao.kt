package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workout: Workout)

    @Update
    suspend fun update(workout: Workout)

    @Delete
    suspend fun delete(workout: Workout)

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkout(id: Int): Flow<Workout>

    @Query("SELECT * FROM workouts")
    fun getWorkouts(): Flow<List<Workout>>

    @Query("SELECT * FROM workouts WHERE isEnabled = :state")
    fun getWorkoutsByState(state: Boolean): Flow<List<Workout>>

    @Query("SELECT (SELECT COUNT(*) FROM workouts) == 0")
    fun isEmpty(): Boolean
}