package com.ieuan.dev.yourworkouts.datasource

import android.app.Application
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(application: Application) {
    private val exerciseDao = WorkoutRoomDatabase.getDatabase(application)!!.exerciseDao()

    suspend fun insert(exercise: Exercise){
        exerciseDao.insert(exercise)
    }

    suspend fun update(exercise: Exercise){
        exerciseDao.update(exercise)
    }

    suspend fun delete(exercise: Exercise){
        exerciseDao.delete(exercise)
    }

    fun getExercise(id: Int): Flow<Exercise>{
        return exerciseDao.getExercise(id)
    }

    fun getExerciseList(): Flow<List<Exercise>>{
        return exerciseDao.getExerciseList()
    }
}