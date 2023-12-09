package com.ieuan.dev.yourworkouts.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    var exerciseName: String = "",
    var numberOfSets: Int = 0,
    var numberOfReps: Int = 0,
    var exerciseWeight: Float = 0f,
    var isDropSetEnabled: Boolean = false,
    var exerciseDropSetWeightOne: Float = 0f,
    var exerciseDropSetWeightTwo: Float = 0f,
    var exerciseDropSetWeightThree: Float = 0f,
    var exerciseImageUri: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
