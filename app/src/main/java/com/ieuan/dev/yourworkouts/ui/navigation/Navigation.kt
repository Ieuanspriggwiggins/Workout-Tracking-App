package com.ieuan.dev.yourworkouts.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SportsMartialArts
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ieuan.dev.yourworkouts.R
import com.ieuan.dev.yourworkouts.ui.components.IconGroup

@Composable
fun MainNavigationBar(
    navController: NavController
) {

    val icons = mapOf(
        Screen.Home to IconGroup(
            filledIcon = Icons.Filled.Home,
            outlinedIcon = Icons.Outlined.Home,
            label = stringResource(id = R.string.home_nav)
        ),
        Screen.Schedule to IconGroup(
            filledIcon = Icons.Filled.CalendarMonth,
            outlinedIcon = Icons.Outlined.CalendarMonth,
            label = stringResource(id = R.string.schedule_nav)
        ),
        Screen.Exercises to IconGroup(
            filledIcon = Icons.Filled.SportsMartialArts,
            outlinedIcon = Icons.Outlined.SportsMartialArts,
            label = stringResource(id = R.string.exercise_nav)
        )
    )

    NavigationBar{
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            val labelText = icons[screen]!!.label
            val isSelected = currentDestination?.hierarchy?.any{it.route == screen.route} == true

            NavigationBarItem(
                selected = isSelected,
                icon = {
                    Icon(
                        contentDescription = labelText,
                        imageVector = (icons[screen]!!.outlinedIcon)
                    )
                },
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(labelText)
                }
            )
        }
    }
}