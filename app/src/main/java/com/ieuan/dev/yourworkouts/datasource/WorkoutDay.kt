package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_day")
data class WorkoutDay(
    var workoutName: String = "",
    var workoutLength: String = "",
    var isEnabled: Boolean = false,
    @PrimaryKey
    var workoutDay: Days
)

/**
 * Enum used for clarification of the day a workout falls on
 */
enum class Days{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    /**
     * Converts a day enum class into a string
     */
    fun dayToString(day: Days): String {
        return when(day){
            MONDAY -> "Monday"
            TUESDAY -> "Tuesday"
            WEDNESDAY -> "Wednesday"
            THURSDAY -> "Thursday"
            FRIDAY -> "Friday"
            SATURDAY -> "Saturday"
            SUNDAY -> "Sunday"
        }
    }
}

