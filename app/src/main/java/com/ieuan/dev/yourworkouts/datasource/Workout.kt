package com.ieuan.dev.yourworkouts.datasource

import android.app.Application
import android.content.Context
import androidx.compose.ui.res.stringResource
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ieuan.dev.yourworkouts.R

@Entity(tableName = "workouts")
data class Workout(
    var workoutName: String = "",
    var dayOfWeek: Days = Days.MONDAY, //Default value
    var isEnabled: Boolean = false,
    var workoutLength: String = "",
    @PrimaryKey(autoGenerate = false)
    var id: Int,
)

fun dayToString(day: Days, context: Context): String {
    return when(day){
        Days.MONDAY -> context.getString(R.string.day_monday)
        Days.TUESDAY -> context.getString(R.string.day_tuesday)
        Days.WEDNESDAY -> context.getString(R.string.day_wednesday)
        Days.THURSDAY -> context.getString(R.string.day_thursday)
        Days.FRIDAY -> context.getString(R.string.day_friday)
        Days.SATURDAY -> context.getString(R.string.day_saturday)
        Days.SUNDAY -> context.getString(R.string.day_sunday)
    }
}

enum class Days {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

/**
 * Default workout list
 */

var defaultWorkoutList = listOf(
    //A minimum of one workout/day must be enabled at any given time
    Workout(dayOfWeek = Days.MONDAY, id = 0, isEnabled = true),
    Workout(dayOfWeek = Days.TUESDAY, id = 1),
    Workout(dayOfWeek = Days.WEDNESDAY, id = 2),
    Workout(dayOfWeek = Days.THURSDAY, id = 3),
    Workout(dayOfWeek = Days.FRIDAY, id = 4),
    Workout(dayOfWeek = Days.SATURDAY, id = 5),
    Workout(dayOfWeek = Days.SUNDAY, id = 6)
)