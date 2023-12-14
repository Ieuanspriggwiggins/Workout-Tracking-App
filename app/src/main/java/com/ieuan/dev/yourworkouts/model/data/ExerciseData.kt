package com.ieuan.dev.yourworkouts.model.data

import android.net.Uri

data class ExerciseData(
    var exerciseName: String = "",
    var numberOfSets: String = "",
    var numberOfReps: String = "",
    var exerciseWeight: String = "",
    var isDropSetEnabled: Boolean = false,
    var exerciseDropSetWeightOne: String = "",
    var exerciseDropSetWeightTwo: String = "",
    var exerciseDropSetWeightThree: String = "",
)

fun isValidInt(s: String): Boolean {
    return s.toIntOrNull() != null
}

fun isValidFloat(s: String): Boolean {
    return s.toFloatOrNull() != null
}


/**
 * Converts the data state data object into a valid exercise entity for database entry
 * checks if the values are transferable to the given type, otherwise app would crash if a value
 * is empty when creating the entity
 */
fun dataStateToExerciseEntity(exerciseData: ExerciseData, exerciseImageUri: Uri?): Exercise {
    //Validate the data in dataState
    val defaultIntValue = 0
    val defaultFloatValue = 0f
    return Exercise(
        exerciseName = exerciseData.exerciseName,
        numOfSets = exerciseData.numberOfSets.toIntOrNull() ?: defaultIntValue,
        numOfReps = exerciseData.numberOfReps.toIntOrNull() ?: defaultIntValue,
        exerciseWeight = exerciseData.exerciseWeight.toFloatOrNull() ?: defaultFloatValue,
        isDropSet = exerciseData.isDropSetEnabled,
        dropSetFirstWeight = exerciseData.exerciseDropSetWeightOne.toFloatOrNull()
            ?: defaultFloatValue,
        dropSetSecondWeight = exerciseData.exerciseDropSetWeightTwo.toFloatOrNull()
            ?: defaultFloatValue,
        dropSetThirdWeight = exerciseData.exerciseDropSetWeightThree.toFloatOrNull()
            ?: defaultFloatValue,
        exerciseImage = exerciseImageUri.toString()
    )
}