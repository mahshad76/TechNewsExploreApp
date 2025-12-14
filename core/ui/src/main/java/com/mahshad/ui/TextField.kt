package com.mahshad.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextFiled(
    value: String,
    onValueChanged: (String) -> Unit,
    placeHolder: @Composable () -> Unit,
    cornerRadius: Int,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        placeholder = { placeHolder() },
        shape = RoundedCornerShape(cornerRadius.dp),
        modifier = modifier
    )
}