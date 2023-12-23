package com.ieuan.dev.yourworkouts.datasource

import android.app.Application
import kotlinx.coroutines.flow.Flow

class ExerciseScheduleLinkRepository(application: Application) {
    private val exerciseScheduleLinkDao = WorkoutRoomDatabase.getDatabase(application)!!.exerciseScheduleLinkDao()

    suspend fun insert(exerciseScheduleLink: ExerciseScheduleLink){
        exerciseScheduleLinkDao.insert(exerciseScheduleLink)
    }

    suspend fun delete(exerciseScheduleLink: ExerciseScheduleLink){
        exerciseScheduleLinkDao.delete(exerciseScheduleLink)
    }

    fun getExercisesNotInSchedule(workoutDay: String): Flow<List<Exercise>> =
        exerciseScheduleLinkDao.getExercisesNotInSchedule(workoutDay)

    fun getExercisesInSchedule(workoutDay: String): Flow<List<Exercise>> =
        exerciseScheduleLinkDao.getExerciseInSchedule(workoutDay)

}