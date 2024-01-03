/**
 * @author ieuan sprigg-wiggins
 * Specifies the database schema for the application
 */
package com.ieuan.dev.yourworkouts.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Exercise::class,
        WorkoutDay::class,
        ExerciseScheduleLink::class
               ],
    version = 1,
    exportSchema = false)
abstract class WorkoutRoomDatabase : RoomDatabase() {

    //references to the data access objects for the database
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDayDao(): WorkoutDayDao
    abstract fun exerciseScheduleLinkDao(): ExerciseScheduleLinkDao

    //Companion object for creating an instance of the database.
    companion object {
        private var instance: WorkoutRoomDatabase? = null

        //Synchronized so only one version of the database is created in the
        //application
        @Synchronized
        fun getDatabase(context: Context): WorkoutRoomDatabase? {
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutRoomDatabase::class.java,
                    "workout_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}