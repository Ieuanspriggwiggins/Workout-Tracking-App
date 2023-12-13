package com.ieuan.dev.yourworkouts.ui.exercise

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.ieuan.dev.yourworkouts.model.data.Exercise
import com.ieuan.dev.yourworkouts.model.data.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class ExerciseViewModel(application: Application): AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)

    var exercises: Flow<List<Exercise>> = exerciseRepository.getExerciseList()
        private set

}
