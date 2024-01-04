package com.ieuan.dev.yourworkouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImagePainter
import com.ieuan.dev.yourworkouts.datasource.Days
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay
import com.ieuan.dev.yourworkouts.datasource.WorkoutDayRepository
import com.ieuan.dev.yourworkouts.ui.exercise.CreateExerciseScreen
import com.ieuan.dev.yourworkouts.ui.exercise.EditExerciseScreen
import com.ieuan.dev.yourworkouts.ui.exercise.ExerciseScreen
import com.ieuan.dev.yourworkouts.ui.home.HomeScreen
import com.ieuan.dev.yourworkouts.ui.navigation.LoadingScreen
import com.ieuan.dev.yourworkouts.ui.schedule.AddExerciseToScheduleScreen
import com.ieuan.dev.yourworkouts.ui.schedule.AddWorkoutDayScreen
import com.ieuan.dev.yourworkouts.ui.schedule.EditWorkoutDayScreen
import com.ieuan.dev.yourworkouts.ui.schedule.ScheduleScreen
import com.ieuan.dev.yourworkouts.ui.theme.YourWorkoutsTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

val TAG: String = "TAG_SC"

class MainActivity : ComponentActivity() {

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

    /**
     * Sets up the database with the data the first time the app is launched or the
     * cache was cleared
     */
    private fun setupDatabase(navController: NavController) {
        lifecycleScope.launch {
            val workoutDayRepository = WorkoutDayRepository(application)
            val testList: List<WorkoutDay> = workoutDayRepository.getWorkouts().first()
            //Assume it is empty, and populate, otherwise continue as normal
            if(testList.isEmpty()){
                workoutDayRepository.populateDefaultValues()
            }
            //Once the task is done, navigate to the homepage
            navController.navigate("home")
        }
    }

    @Composable
    fun WorkoutAppStart() {
        val navController = rememberNavController()

        //Start with the loading screen when the app first opens
        NavHost(navController = navController, startDestination = "loadingscreen"){
            composable("loadingscreen") { LoadingScreen() }
            composable("home") { HomeScreen(navController = navController)}
            composable("schedule") {ScheduleScreen(navController = navController)}
            composable("exercises") {ExerciseScreen(navController = navController)}
            composable("createExercise") { CreateExerciseScreen(navController = navController) }
            composable(
                route = "editExercise/{exerciseId}",
                arguments = listOf(navArgument("exerciseId"){type = NavType.IntType})
            ) {EditExerciseScreen(navController = navController)}
            composable("addWorkoutDayScreen") { AddWorkoutDayScreen(navController = navController)}
            composable(
                route = "editWorkoutScreen/{workoutDay}",
                arguments = listOf(navArgument("workoutDay"){type = NavType.StringType})
            ) { EditWorkoutDayScreen(navController = navController) }
            composable(
                route = "addExerciseToScheduleScreen/{workoutDay}",
                arguments = listOf(navArgument("workoutDay"){type = NavType.StringType})
            ) { AddExerciseToScheduleScreen(navController = navController)}
        }

        setupDatabase(navController)
    }
}