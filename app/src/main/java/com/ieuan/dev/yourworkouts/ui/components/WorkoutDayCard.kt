/**
 * @author ieuan sprigg-wiggins
 * The workout day card displayed on the schedule top level screen. Contains the day
 * of the workout day and the name of the workout day that is specified by the user
 * (if they wish)
 */

package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDayCard(
    navController: NavController,
    workout: WorkoutDay
) {

    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(0),
        onClick = {
            val workoutDay = workout.workoutDay.toString()
            navController.navigate("editWorkoutScreen/$workoutDay")
        }
    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            var outputString: String = workout.workoutDay.dayToString(LocalContext.current)
            if(workout.workoutName.isNotEmpty()){
                outputString += ": " + workout.workoutName
            }
            Text(text = outputString)
            Icon(
                contentDescription = null,
                imageVector = Icons.Filled.Edit
            )
        }
    }
}