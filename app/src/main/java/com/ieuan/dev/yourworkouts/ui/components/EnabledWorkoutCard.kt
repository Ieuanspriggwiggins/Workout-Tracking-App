package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.datasource.Workout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnabledWorkoutCard(
    navController: NavController,
    workout: Workout
) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(0),
        onClick = {
            val id = workout.id
            navController.navigate("editWorkoutScreen/$id")
        }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Column {
                Text(text = workout.workoutName)
            }
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null
            )
        }
    }
}