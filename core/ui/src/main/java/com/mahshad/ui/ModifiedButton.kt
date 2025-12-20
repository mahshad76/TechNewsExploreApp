package com.mahshad.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun ModifiedButton(
    content: @Composable () -> Unit,
    onClick: () -> Unit,
    shape: Shape,
    buttonColors: ButtonColors,
    borderStroke: BorderStroke,
    enabled: Boolean = false,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = shape,
        colors = buttonColors,
        border = borderStroke,
        enabled = enabled,
    ) {
        content()
    }
}