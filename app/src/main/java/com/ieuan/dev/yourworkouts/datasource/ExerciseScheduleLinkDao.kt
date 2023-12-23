package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseScheduleLinkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseScheduleLink: ExerciseScheduleLink)

    @Delete
    suspend fun delete(exerciseScheduleLink: ExerciseScheduleLink)

    @Query("SELECT e.* FROM exercise e WHERE e.id NOT IN (SELECT esl.exerciseId FROM exerciseschedulelink esl WHERE workoutDay = :workoutDay)")
    fun getExercisesNotInSchedule(workoutDay:String): Flow<List<Exercise>>

    /**
     * Get all the exercises added to the current schedule
     */
    @Query("SELECT e.* FROM exercise e WHERE e.id IN (SELECT esl.exerciseId FROM exerciseschedulelink esl WHERE workoutDAy = :workoutDay)")
    fun getExerciseInSchedule(workoutDay: String): Flow<List<Exercise>>
}