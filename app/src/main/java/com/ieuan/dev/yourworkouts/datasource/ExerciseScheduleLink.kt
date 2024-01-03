/**
 * @author ieuan sprigg-wiggins
 * Entity data class for the links between the exercises and the workout day in the
 * schedule they are related to. Stores the exercise id and the workout day as a foreign
 * key for the link between them
 */

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
