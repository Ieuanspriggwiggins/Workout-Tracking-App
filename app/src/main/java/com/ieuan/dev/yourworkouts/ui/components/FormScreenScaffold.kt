/**
 * @author ieuan sprigg-wiggins
 * Reusable composable component for the form scaffold used throughout the application
 */

package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ieuan.dev.yourworkouts.R

@Composable
@Preview
fun FormScreenScaffoldPreview() {
    FormScreenScaffold(
        navController = rememberNavController(),
        formTitle = "Test"
    ){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreenScaffold(
    navController: NavController,
    formTitle: String,
    onSaveClick: () -> Unit = {},
    bottomLeftButtonText: String = stringResource(R.string.save_btn),
    bottomRightButtonText: String = stringResource(R.string.cancel_btn),
    hasFabIcon: Boolean = false,
    fabOnclick: () -> Unit = {},
    actions: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(formTitle)
                },
                actions = {
                    actions()
                },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },

        floatingActionButton = {
            if(hasFabIcon){
                FloatingActionButton(
                    onClick = fabOnclick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp, start = 24.dp)
            ){
                Button(onClick = onSaveClick) {
                    Text(text = bottomLeftButtonText)
                }
                OutlinedButton(
                    onClick = {navController.popBackStack()},
                    modifier = Modifier
                        .padding(start = 12.dp)
                ) {
                    Text(text = bottomRightButtonText)
                }
            }
        }

    ){ values ->
        Surface(
            modifier = Modifier
                .padding(values)
        ){
            content()
        }
    }
}