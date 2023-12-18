package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    var workoutName: String = "",
    var dayOfWeek: Days = Days.Monday, //Default value
    var isEnabled: Boolean = false,
    var workoutLength: String = "",
    @PrimaryKey(autoGenerate = false)
    var id: Int,
)

enum class Days {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}

/**
 * Default workout list
 */

var defaultWorkoutList = listOf(
    //A minimum of one workout/day must be enabled at any given time
    Workout(dayOfWeek = Days.Monday, id = 0, isEnabled = true),
    Workout(dayOfWeek = Days.Tuesday, id = 1),
    Workout(dayOfWeek = Days.Wednesday, id = 2),
    Workout(dayOfWeek = Days.Thursday, id = 3),
    Workout(dayOfWeek = Days.Friday, id = 4),
    Workout(dayOfWeek = Days.Saturday, id = 5),
    Workout(dayOfWeek = Days.Sunday, id = 6)
)