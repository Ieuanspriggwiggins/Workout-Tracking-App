package com.ieuan.dev.yourworkouts.ui.components

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ieuan.dev.yourworkouts.model.data.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseCard(
    exercise: Exercise,
    onClick: () -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        shape = RoundedCornerShape(4.dp),
        onClick = onClick
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(
                text = exercise.exerciseName,
                fontSize = 18.sp
            )
            if(exercise.exerciseImage.isNotEmpty()){
                AsyncImage(
                    model = Uri.parse(exercise.exerciseImage),
                    contentDescription = exercise.exerciseName
                )
            }
        }
    }
}

