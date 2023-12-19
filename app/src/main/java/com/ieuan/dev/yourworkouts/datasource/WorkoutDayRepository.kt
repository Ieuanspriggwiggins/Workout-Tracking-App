package com.ieuan.dev.yourworkouts.datasource

import android.app.Application
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class WorkoutDayRepository(application: Application) {
    private val workoutDayDao = WorkoutRoomDatabase.getDatabase(application)!!.workoutDayDao()

    suspend fun insert(workoutDay: WorkoutDay) = workoutDayDao.insert(workoutDay)

    suspend fun update(workoutDay: WorkoutDay) = workoutDayDao.update(workoutDay)

    suspend fun delete(workoutDay: WorkoutDay) = workoutDayDao.delete(workoutDay)

    fun getWorkout(day: Days): Flow<WorkoutDay> = workoutDayDao.getWorkout(day)

    fun getWorkouts(): Flow<List<WorkoutDay>> = workoutDayDao.getWorkouts()
}