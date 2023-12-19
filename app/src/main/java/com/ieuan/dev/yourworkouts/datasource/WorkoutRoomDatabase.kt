package com.ieuan.dev.yourworkouts.datasource

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Database(
    entities = [Exercise::class],
    version = 1,
    exportSchema = false)
abstract class WorkoutRoomDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDayDao(): WorkoutDayDao

    companion object {
        private var instance: WorkoutRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        @Synchronized
        fun getDatabase(context: Context): WorkoutRoomDatabase? {
            if(instance == null){
                instance = Room.databaseBuilder<WorkoutRoomDatabase>(
                    context.applicationContext,
                    WorkoutRoomDatabase::class.java,
                    "workout_db"
                )
                    .allowMainThreadQueries()
                    .addCallback(roomDatabaseCallback(context))
                    .build()
            }
            return instance
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object: Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            }
        }
    }
}