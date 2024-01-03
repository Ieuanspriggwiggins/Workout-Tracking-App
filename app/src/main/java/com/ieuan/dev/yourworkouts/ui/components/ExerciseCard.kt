/**
 * @author ieuan sprigg-wiggins
 * Reusable composable component for displaying exercises data and image
 * for the user interface
 */

package com.ieuan.dev.yourworkouts.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.datasource.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseCard(
    exercise: Exercise,
    modifier: Modifier = Modifier,
    extraContent: @Composable () -> Unit = {},
    onClick: () -> Unit = {}
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(
                text = exercise.exerciseName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 12.dp)
            )
            //If the user hasn't selected an image for the exercise, the image shouldn't be shown.
            if(exercise.exerciseImage != "null"){
                AsyncImage(
                    model = Uri.parse(exercise.exerciseImage),
                    contentDescription = exercise.exerciseName,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .heightIn(max = 200.dp)
                )
            }
            //If the exercise isn't a drop set we show the weight.
            if(!exercise.isDropSet){
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = stringResource(id = R.string.exercise_card_weight_label))
                    Text(
                        text = exercise.exerciseWeight.toString() + "kg",
                        modifier = Modifier
                            .padding(start = 5.dp)
                    )
                }
            }

            //if the exercise weight isn't being displayed, we don't need this divider
            if(!exercise.isDropSet){ Divider(color = Color.Black) }
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = stringResource(id = R.string.exercise_card_set_label))
                Text(
                    text = exercise.numOfSets.toString(),
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            //If the exercise isn't a drop set
            if(!exercise.isDropSet){
                Divider(color = Color.Black)
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(id = R.string.exercise_card_rep_label))
                    Text(
                        text = exercise.numOfReps.toString(),
                        modifier = Modifier.padding(start = 5.dp),
                    )
                }
            }else{
                Divider(color = Color.Black)
                Column{
                    Text(
                        text = stringResource(id = R.string.exercise_card_drop_set_label),
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
                    ){
                        Text(text = exercise.dropSetFirstWeight.toString() + "kg")
                        Text(text = exercise.dropSetSecondWeight.toString() + "kg")
                        Text(text = exercise.dropSetThirdWeight.toString() + "kg")
                    }
                }
            }
            //Extra, can be used if further content is required below the card
            extraContent()
        }
    }
}

