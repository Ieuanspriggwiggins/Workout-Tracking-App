package com.ieuan.dev.yourworkouts.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExercise(id: Int): Flow<Exercise>

    @Query("SELECT * FROM exercise")
    fun getExerciseList(): Flow<List<Exercise>>
}