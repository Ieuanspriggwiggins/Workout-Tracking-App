/**
 * @author ieuan sprigg-wiggins
 * The top level scaffold displayed on the main three screens for the app
 */

package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ieuan.dev.yourworkouts.ui.navigation.MainNavigationBar


@Composable
@Preview
fun TopLevelScaffoldPreview() {
    TopLevelScaffold(
        navController = rememberNavController(),
        screenTitle = "Test"
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelScaffold(
    navController: NavController,
    screenTitle: String,
    hasFabIcon: Boolean = false,
    fabOnclick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        //Bottom app bar for navigation
        bottomBar = {
            MainNavigationBar(navController = navController)
        },

        topBar = {
            Text(
                textAlign = TextAlign.Center,
                lineHeight = 35.sp,
                fontSize = 24.sp,
                text = screenTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        },

        //Content differs depending on the top level screen the user is on
        content = { values ->
            Surface(
                modifier = Modifier.padding(values)
            ){
                content()
            }
        },

        //Floating action button only appears if requested by the composable calling it
        floatingActionButton = {
            if(hasFabIcon){
                FloatingActionButton(
                    onClick = fabOnclick
                ) {
                    Icon(
                       imageVector = (Icons.Filled.Add),
                        contentDescription = null
                    )
                }
            }
        }
    )
}