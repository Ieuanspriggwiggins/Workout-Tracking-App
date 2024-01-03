/**
 * @author ieuan sprigg-wiggins
 * Composable for the loading screen used when the application first starts. Acts as a
 * gap for the database to get ready before the application goes to the home screen to
 * trying to access a null object as the database wouldn't necessarily have the information
 * yet for the default values
 */

package com.ieuan.dev.yourworkouts.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            CircularProgressIndicator(
                modifier = Modifier.width(80.dp),
            )
    }
}