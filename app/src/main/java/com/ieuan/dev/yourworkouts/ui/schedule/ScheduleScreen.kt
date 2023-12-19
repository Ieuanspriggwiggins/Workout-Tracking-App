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
import com.ieuan.dev.yourworkouts.ui.components.TopLevelScaffold
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScheduleScreen(
    navController: NavController,
) {

    TopLevelScaffold(
        navController = navController,
        screenTitle = stringResource(id = R.string.schedule_screen_title),
        hasFabIcon = true,
        fabOnclick = {

        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 32.dp, start = 8.dp, end = 8.dp)
        ) {

        }
    }
}