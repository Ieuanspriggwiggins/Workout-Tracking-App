/**
 * @author ieuan sprigg-wiggins
 * Composable used on the screen to add a workout day to users enabled workout days
 * contains a checkbox to specify the state set by the user for the specific workout
 * day
 */

package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDayCheckboxCard(workout: WorkoutDay) {

    var isChecked by remember {mutableStateOf(false)}
    workout.isEnabled = isChecked

    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(0),
        onClick = {
            isChecked = !isChecked
        }
    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = workout.workoutDay.dayToString(LocalContext.current))
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = !isChecked
                }
            )
        }
    }
}