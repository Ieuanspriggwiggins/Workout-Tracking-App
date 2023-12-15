package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlertDialogComponent(
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
){
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = content)
        },
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)

    )
}