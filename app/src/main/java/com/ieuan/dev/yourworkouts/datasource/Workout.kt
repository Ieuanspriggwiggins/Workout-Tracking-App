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