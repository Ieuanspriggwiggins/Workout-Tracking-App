package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ieuan.dev.yourworkouts.datasource.Days
import com.ieuan.dev.yourworkouts.datasource.Exercise
import com.ieuan.dev.yourworkouts.datasource.ExerciseScheduleLinkRepository
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.Flow

class HomeScreenViewModel(
    application: Application
): AndroidViewModel(application) {
    private val workoutDayRepository = WorkoutDayRepository(application)
    private val scheduleLinkRepository = ExerciseScheduleLinkRepository(application)

    /**
     * Check if the database table is empty, if so, then needs populating
     * as the app has been started for the first time (or the database has been
     * deleted through some other process)
     */
    init{
        viewModelScope.launch{
            val testList: List<WorkoutDay> = workoutDayRepository.getWorkouts().first()
            //Assume it is empty, and populate, otherwise continue as normal
            if(testList.isEmpty()){
                workoutDayRepository.populateDefaultValues()
            }
        }
    }

    //Generate the title for the home screen
    private val calendar: Calendar = Calendar.getInstance()
    private val date = calendar.time
    //To ensure compatibility with different languages

    private val currentDayEnglish: String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)

    //Use the days enum to get the current day from the strings xml file for language localisation
    val currentDayString = Days.valueOf(currentDayEnglish.uppercase()).dayToString(application)

    val exerciseList = scheduleLinkRepository.getExercisesInSchedule(currentDayEnglish.uppercase())

    //Get the workout day object for the name of the workout
    val workoutDay = workoutDayRepository.getWorkout(Days.valueOf(currentDayEnglish.uppercase()))
}