package com.ieuan.dev.yourworkouts.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeScreenViewModel(
    application: Application
): AndroidViewModel(application) {


    //Generate the title for the home screen
    private val calendar: Calendar = Calendar.getInstance()
    private val date = calendar.time
    private val currentDay = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
    val screenTitle = "Your Workout: $currentDay"
}