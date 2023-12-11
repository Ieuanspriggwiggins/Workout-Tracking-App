package com.ieuan.dev.yourworkouts.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    val exerciseName: String = "",
    val numOfSets: Int = 0,
    val numOfReps: Int = 0,
    val exerciseWeight: Float = 0f,
    val isDropSet: Boolean = false,
    val dropSetFirstWeight: Float = 0f,
    val dropSetSecondWeight: Float = 0f,
    val dropSetThirdWeight: Float = 0f,
    val exerciseImage: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
