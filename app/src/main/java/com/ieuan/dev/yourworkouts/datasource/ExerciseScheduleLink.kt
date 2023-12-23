package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExerciseScheduleLink")
data class ExerciseScheduleLink(
    var workoutDay: Days,
    var exerciseId: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
)
