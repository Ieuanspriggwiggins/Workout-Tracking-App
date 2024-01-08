/**
 * @author ieuan sprigg-wiggins
 * Top level screen for displaying the existing exercises in the application.
 */

package com.ieuan.dev.yourworkouts.ui.exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.ExerciseViewModel
import com.ieuan.dev.yourworkouts.ui.components.ExerciseCard
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold


@Composable
fun ExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = viewModel()
) {

    val exerciseList by viewModel.exercises.collectAsState(listOf())

    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(id = R.string.exercise_screen_title),
        hasFabIcon = true,
        fabOnclick = {
            navController.navigate("createExercise")
        }
    ) {

        //if the exercise list is empty (none exist in the application)
        //display a message stating this
        if(exerciseList.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(R.string.warning_no_exercise_list),
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxHeight()
        ){
            items(exerciseList){ exercise ->
                ExerciseCard(
                    exercise = exercise,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                ) {
                    val id: Int = exercise.id
                    navController.navigate("editExercise/$id")
                }
            }
            //Adds some extra space so the FAB icon isn't in the way of any information on the exercise cards.
            item(){
                Spacer(modifier = Modifier.padding(bottom = 50.dp))
            }
        }
    }
}