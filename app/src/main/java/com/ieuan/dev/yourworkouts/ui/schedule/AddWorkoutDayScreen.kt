/**
 * @author ieuan sprigg-wiggins
 * Composable for the screen for adding a workout day to the users list of
 * workout days they have enabled
 */

package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.model.AddWorkoutDayViewModel
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ieuan.dev.yourworkouts.ui.components.WorkoutDayCheckboxCard

@Composable
fun AddWorkoutDayScreen(
    navController: NavController,
    viewModel: AddWorkoutDayViewModel = viewModel()
) {

    //Get the disabled list as state from the view model
    val list by viewModel.disabledDayList.collectAsState(initial = listOf())

    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.add_days_to_schedule_title),
        bottomLeftButtonText = stringResource(R.string.add_btn),
        onSaveClick = {
            viewModel.updateList(list)
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 24.dp)
        ){
            list.forEach{ workout ->
                WorkoutDayCheckboxCard(workout)
            }
        }
    }
}