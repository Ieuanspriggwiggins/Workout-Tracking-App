package com.ieuan.dev.yourworkouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.ieuan.dev.yourworkouts.model.data.WorkoutRoomDatabase
import com.ieuan.dev.yourworkouts.ui.exercise.CreateExerciseScreen
import com.ieuan.dev.yourworkouts.ui.exercise.CreateExerciseViewModel
import com.ieuan.dev.yourworkouts.ui.exercise.ExerciseScreen
import com.ieuan.dev.yourworkouts.ui.home.HomeScreen
import com.ieuan.dev.yourworkouts.ui.schedule.ScheduleScreen
import com.ieuan.dev.yourworkouts.ui.theme.YourWorkoutsTheme

class MainActivity : ComponentActivity() {

    //Create the database used by the app
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            WorkoutRoomDatabase::class.java,
            "workout_db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourWorkoutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WorkoutAppStart()
                }
            }
        }
    }

    @Composable
    fun WorkoutAppStart() {
        val navController = rememberNavController()
        
        NavHost(navController = navController, startDestination = "home"){
            composable("home") { HomeScreen(navController = navController)}
            composable("schedule") {ScheduleScreen(navController = navController)}
            composable("exercises") {ExerciseScreen(navController = navController)}
            composable("createExercise") { CreateExerciseScreen(
                navController = navController,
                CreateExerciseViewModel(db.exerciseDao)
            ) }
        }
    }
}