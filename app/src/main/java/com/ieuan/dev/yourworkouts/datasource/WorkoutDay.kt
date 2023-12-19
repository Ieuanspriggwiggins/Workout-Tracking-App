package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_day")
data class WorkoutDay(
    var workoutName: String = "",
    var workoutLength: String = "",
    @PrimaryKey
    var workoutDay: Days
)

/**
 * Enum used for clarification of the day a workout falls on
 */
enum class Days{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

fun dayToString(day: Days): String {
    return when(day){
        Days.MONDAY -> "Monday"
        Days.TUESDAY -> "Tuesday"
        Days.WEDNESDAY -> "Wednesday"
        Days.THURSDAY -> "Thursday"
        Days.FRIDAY -> "Friday"
        Days.SATURDAY -> "Saturday"
        Days.SUNDAY -> "Sunday"
    }
}