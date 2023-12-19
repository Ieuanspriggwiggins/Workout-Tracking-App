package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold

@Composable
fun AddWorkoutDayScreen(
    navController: NavController
) {
    FormScreenScaffold(
        navController = navController,
        formTitle = stringResource(R.string.add_days_to_schedule_title),
        bottomLeftButtonText = stringResource(R.string.add_btn),
        onSaveClick = {
            navController.popBackStack()
        }
    ) {

    }
}