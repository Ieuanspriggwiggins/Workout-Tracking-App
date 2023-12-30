package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ieuan.dev.yourworkouts.datasource.Days
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.datasource.ExerciseScheduleLinkRepository
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.Flow

class HomeScreenViewModel(
    application: Application
): AndroidViewModel(application) {
    private val workoutDayRepository = WorkoutDayRepository(application)
    private val scheduleLinkRepository = ExerciseScheduleLinkRepository(application)

    //Generate the title for the home screen
    private val calendar: Calendar = Calendar.getInstance()
    private val date = calendar.time
    //To ensure compatibility with different languages

    private val currentDayEnglish: String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)

    //Use the days enum to get the current day from the strings xml file for language localisation
    val currentDayString = Days.valueOf(currentDayEnglish.uppercase()).dayToString(application)

    val exerciseList = scheduleLinkRepository.getExercisesInSchedule(currentDayEnglish.uppercase())


}