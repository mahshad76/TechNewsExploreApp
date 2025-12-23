package com.mahshad.ui.components

import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ModifiedToggleButton(
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit,
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = { onClick() },
        enabled = true,
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            checkedContainerColor = Color.White,
            checkedContentColor = Color.Black
        ),
        modifier = modifier
    ) {
        if (checked) icon() else checkedIcon()
    }
}