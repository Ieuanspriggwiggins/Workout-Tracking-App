package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    var workoutName: String = "",
    var dayOfWeek: days = days.MONDAY, //Default value
    var isEnabled: Boolean = false,
    @PrimaryKey(autoGenerate = false)
    var id: Int,
)

enum class days {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

/**
 * Default workout list
 */

var defaultWorkoutList = listOf(
    Workout(workoutName = "Monday", dayOfWeek = days.MONDAY, id = 0),
    Workout(workoutName = "Tuesday", dayOfWeek = days.TUESDAY, id = 1),
    Workout(workoutName = "Wednesday", dayOfWeek = days.WEDNESDAY, id = 2),
    Workout(workoutName = "Thursday", dayOfWeek = days.THURSDAY, id = 3),
    Workout(workoutName = "Friday", dayOfWeek = days.FRIDAY, id = 4),
    Workout(workoutName = "Saturday", dayOfWeek = days.SATURDAY, id = 5),
    Workout(workoutName = "Sunday", dayOfWeek = days.SUNDAY, id = 6)
)