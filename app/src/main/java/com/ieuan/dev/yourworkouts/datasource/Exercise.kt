/**
 * @author ieuan sprigg-wiggins
 *
 * File contains the entity data class for the exercises in the database
 */

package com.ieuan.dev.yourworkouts.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    var exerciseName: String = "",
    var numOfSets: Int = 0,
    var numOfReps: Int = 0,
    var exerciseWeight: Float = 0f,
    var isDropSet: Boolean = false,
    var dropSetFirstWeight: Float = 0f,
    var dropSetSecondWeight: Float = 0f,
    var dropSetThirdWeight: Float = 0f,
    var exerciseImage: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
