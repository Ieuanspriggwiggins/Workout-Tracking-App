package com.ieuan.dev.yourworkouts.datasource

import android.app.Application
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(application: Application) {
    private val workoutDao = WorkoutRoomDatabase.getDatabase(application)!!.workoutDao()

    suspend fun insert(workout: Workout) = workoutDao.insert(workout)

    suspend fun update(workout: Workout) = workoutDao.update(workout)

    suspend fun delete(workout: Workout) = workoutDao.delete(workout)

    fun getWorkouts(): Flow<List<Workout>> = workoutDao.getWorkouts()

    fun getWorkout(id: Int):Flow<Workout> = workoutDao.getWorkout(id)

    fun getWorkoutsByState(enabled: Boolean): Flow<List<Workout>> = workoutDao.getWorkoutsByState(enabled)

    fun isEmpty(): Boolean = workoutDao.isEmpty()

    suspend fun insertDefaultListOfWorkouts() {
        defaultWorkoutList.forEach{ workout ->
            insert(workout)
        }
    }

    /**
     * Updates all the workouts in the database
     */
    suspend fun updateWorkouts(workoutsList: List<Workout>) {
        workoutsList.forEach{workout ->
            update(workout)
        }
    }
}