package com.ieuan.dev.yourworkouts.model

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.model.data.ExerciseData
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import com.ieuan.dev.yourworkouts.model.data.dataStateToExerciseEntity
import kotlinx.coroutines.launch


class CreateExerciseViewModel(application: Application): AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)
    val contentResolver = application.contentResolver
    val flags: Int = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION

    var dataState by mutableStateOf(ExerciseData())

    //Store the image uri for the image given by the user
    var exerciseImageUri = mutableStateOf<Uri?>(null)

    /**
     * Submits the exercises to the database
     */
    fun submitExercise() {

        exerciseImageUri.value?.let{contentResolver.takePersistableUriPermission(it, flags)}

        viewModelScope.launch {
            exerciseRepository.insert(dataStateToExerciseEntity(dataState, exerciseImageUri.value))
        }
    }

}
