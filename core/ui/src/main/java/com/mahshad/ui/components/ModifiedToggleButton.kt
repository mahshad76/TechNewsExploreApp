package com.mahshad.ui.components

import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
        modifier = modifier
    ) {
        if (checked) icon() else checkedIcon()
    }
}