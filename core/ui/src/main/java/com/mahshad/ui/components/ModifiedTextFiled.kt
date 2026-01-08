package com.mahshad.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mahshad.ui.icons.TneIcons.Visibility
import com.mahshad.ui.icons.TneIcons.VisibilityOff

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
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        placeholder = { placeHolder() },
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = color,
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.NumberPassword else KeyboardType.Email
        ),
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Visibility else VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            }
        } else null,
        modifier = modifier
    )
}