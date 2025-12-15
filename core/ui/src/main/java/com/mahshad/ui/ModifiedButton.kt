package com.mahshad.ui

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ModifiedButton(content: @Composable () -> Unit, onClick: () -> Unit, modifier: Modifier) {
    Button(modifier = modifier, onClick = { onClick() }) {
        content()
    }
}