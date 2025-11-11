package com.mahshad.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TextField(
    text: String,
    update: (text: String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    matchPatternError: Boolean
) {
    OutlinedTextField(
        value = text,
        onValueChange = { text: String ->
            update.invoke(text)
        },
        modifier = Modifier
            .width(357.dp)
            .wrapContentHeight()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(18.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = { Text(placeholder) },
        isError = matchPatternError
    )
}