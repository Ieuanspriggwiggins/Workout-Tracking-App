package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.datasource.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class ExerciseViewModel(application: Application): AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)

    var exercises: Flow<List<Exercise>> = exerciseRepository.getExerciseList()
        private set

}
