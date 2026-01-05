package com.mahshad.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.mahshad.home.R
import com.mahshad.ui.modifiers.interceptKey

@Composable
fun HomeSearchBar(
    modifier: Modifier,
    searchQuery: String,
    onInputChanged: (String) -> Unit,
    onSearchSubmit: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchSubmit(searchQuery)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .interceptKey(Key.Enter) {
                onSearchExplicitlyTriggered()
            },
        value = searchQuery,
        onValueChange = {
            if (!it.contains("\n")) {
                onInputChanged(it)
            }
        },
        shape = RoundedCornerShape(18.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        placeholder = { Text(stringResource(R.string.search_article)) },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = stringResource(R.string.search_icon),
                Modifier
                    .size(24.dp)
                    .clickable(true, onClick = onSearchExplicitlyTriggered)
            )

        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
    )
}