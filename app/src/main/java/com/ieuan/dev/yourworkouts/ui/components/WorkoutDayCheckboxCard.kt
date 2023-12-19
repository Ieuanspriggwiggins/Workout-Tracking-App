package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ieuan.dev.yourworkouts.datasource.WorkoutDay

@Composable
fun WorkoutDayCheckboxCard(workoutDay: WorkoutDay) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ){
        Text(text = workoutDay.workoutDay.dayToString(LocalContext.current))
    }
}