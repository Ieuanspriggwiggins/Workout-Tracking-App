package com.ieuan.dev.yourworkouts.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    companion object{
        @Volatile
        private var Instance: WorkoutDatabase? = null

        fun getDatabase(context: Context): WorkoutDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, WorkoutDatabase::class.java, "exercises_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {Instance = it}
            }

        }
    }
}