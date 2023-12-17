package com.ieuan.dev.yourworkouts.ui.schedule

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ieuan.dev.yourworkouts.model.ScheduleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.FormScreenScaffold


@Composable
fun AddScheduleScreen(
    navController: NavController,
    viewModel: ScheduleViewModel = viewModel()
) {
    FormScreenScaffold(
        navController = navController,
        formTitle = "Test",
        bottomLeftButtonText = stringResource(R.string.add_btn),
        bottomRightButtonText = stringResource(R.string.cancel_btn)
    ) {

    }
}