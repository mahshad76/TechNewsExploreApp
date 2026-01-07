package com.mahshad.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModifiedTextFiled(
    value: String,
    onValueChanged: (String) -> Unit,
    placeHolder: @Composable () -> Unit,
    cornerRadius: Int,
    color: TextFieldColors,
    isPassword: Boolean = false,
    modifier: Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        placeholder = { placeHolder() },
        shape = RoundedCornerShape(cornerRadius.dp),
        modifier = modifier,
        colors = color
    )
}