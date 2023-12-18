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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.ieuan.dev.yourworkouts.datasource.Workout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisabledWorkoutCard(
    workout: Workout,
) {

    var boxChecked by remember {mutableStateOf(false)}
    workout.isEnabled = boxChecked

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(0),
        onClick = {
            boxChecked = !boxChecked
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 14.dp, end = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(text = workout.dayOfWeek.toString())
            Checkbox(
                checked = boxChecked,
                onCheckedChange = {
                    boxChecked = !boxChecked
                })
        }
    }
}