package com.ieuan.dev.yourworkouts.datasource

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ieuan.dev.yourworkouts.R

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
enum class Days(){
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

