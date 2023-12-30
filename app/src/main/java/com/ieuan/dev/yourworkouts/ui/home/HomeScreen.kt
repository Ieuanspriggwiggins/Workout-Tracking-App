package com.ieuan.dev.yourworkouts.ui.home

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.model.HomeScreenViewModel
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.datasource.Exercise

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = viewModel()
) {

    val exerciseList by viewModel.exerciseList.collectAsState(listOf())

    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.home_screen_title) + viewModel.currentDayString
    ) {
        LazyColumn(
        ){
            items(exerciseList) {exercise ->
                ExerciseHomeScreenCard(exercise = exercise)
            }
        }
    }
}

@Composable
fun ExerciseHomeScreenCard(exercise: Exercise) {
    if(exercise.isDropSet){

    }else{

    }
}