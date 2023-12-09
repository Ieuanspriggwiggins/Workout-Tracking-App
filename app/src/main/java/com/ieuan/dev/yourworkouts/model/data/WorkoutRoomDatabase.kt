package com.ieuan.dev.yourworkouts.model.data

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1)
abstract class WorkoutRoomDatabase: RoomDatabase() {

    abstract val exerciseDao: ExerciseDao

}