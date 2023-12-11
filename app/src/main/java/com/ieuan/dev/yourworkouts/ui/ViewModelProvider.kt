package com.ieuan.dev.yourworkouts.ui

import android.app.Application
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import com.ieuan.dev.yourworkouts.ui.exercise.CreateExerciseViewModel
import com.ieuan.dev.yourworkouts.MainActivity
import com.ieuan.dev.yourworkouts.database

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CreateExerciseViewModel(database.exerciseDao())
        }
    }
}
