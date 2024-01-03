/**
 * @author ieuan sprigg-wiggins
 * file contains the entity data class for the workout days in the
 * application. Also contains an enum class representing the days of the week
 */

package com.ieuan.dev.yourworkouts.datasource

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ieuan.dev.yourworkouts.R

@Entity(tableName = "workout_day")
data class WorkoutDay(
    var workoutName: String = "",
    var workoutLength: String = "",
    var isEnabled: Boolean = false,
    @PrimaryKey
    var workoutDay: Days = Days.MONDAY
)

/**
 * Enum used for clarification of the day a workout falls on
 */
enum class Days{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    /**
     * Converts the enum into a string, for translation and consistency
     */
    fun dayToString(context: Context): String {
        return when(this){
            MONDAY -> context.getString(R.string.day_monday)
            TUESDAY -> context.getString(R.string.day_tuesday)
            WEDNESDAY -> context.getString(R.string.day_wednesday)
            THURSDAY -> context.getString(R.string.day_thursday)
            FRIDAY -> context.getString(R.string.day_friday)
            SATURDAY -> context.getString(R.string.day_saturday)
            SUNDAY -> context.getString(R.string.day_sunday)
        }
    }
}

