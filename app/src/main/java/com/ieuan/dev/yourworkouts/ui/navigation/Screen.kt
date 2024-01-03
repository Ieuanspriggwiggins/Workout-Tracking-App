/**
 * @author ieuan sprigg-wiggins
 * Sealed class for specifying the top level routes for the main locations accessible in the
 * application
 */

package com.ieuan.dev.yourworkouts.ui.navigation

import androidx.annotation.StringRes
import com.ieuan.dev.yourworkouts.R

sealed class Screen(val route: String, @StringRes val resourceId: Int){
    object Home : Screen("home", R.string.home_nav)
    object Schedule : Screen("schedule", R.string.schedule_nav)
    object Exercises : Screen("exercises", R.string.exercise_nav)
}

val items = listOf(
    Screen.Home,
    Screen.Schedule,
    Screen.Exercises
)


