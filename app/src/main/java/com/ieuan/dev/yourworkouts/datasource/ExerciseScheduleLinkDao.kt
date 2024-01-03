/**
 * data access object for the workout day exercise links
 */

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

    /**
     * Deletes an exercise workout day link from the table by the id that is parsed
     * into the function
     */
    @Query("DELETE FROM exerciseschedulelink WHERE exerciseId = :exerciseId AND workoutDay = :workoutDay")
    suspend fun deleteExerciseByDayAndId(exerciseId: Int, workoutDay: String)

    /**
     * Gets the list of exercises that are not in the current workout day already
     */
    @Query("SELECT e.* FROM exercise e WHERE e.id NOT IN (SELECT esl.exerciseId FROM exerciseschedulelink esl WHERE workoutDay = :workoutDay)")
    fun getExercisesNotInSchedule(workoutDay:String): Flow<List<Exercise>>

    /**
     * Deletes many lists from the database depending on the
     */
    @Query("DELETE FROM exerciseschedulelink WHERE workoutDay = :workoutDay")
    suspend fun deleteExercisesForWorkoutDay(workoutDay: String)

    /**
     * Deletes all links for a given exercise id from all workout days
     */
    @Query("DELETE FROM exerciseschedulelink WHERE exerciseId = :exerciseId")
    suspend fun deleteScheduleLinksByExerciseId(exerciseId: Int)

    /**
     * Get all the exercises added to the current schedule
     */
    @Query("SELECT e.* FROM exercise e WHERE e.id IN (SELECT esl.exerciseId FROM exerciseschedulelink esl WHERE workoutDAy = :workoutDay)")
    fun getExerciseInSchedule(workoutDay: String): Flow<List<Exercise>>
}