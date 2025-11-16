package com.mahshad.home

import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchSuggestionsBox(
    suggestions: List<String>,
    onSuggestionClick: (String) -> Unit
) {
    if (suggestions.isNotEmpty()) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 4.dp
        ) {
            LazyColumn(
                modifier = Modifier
                    .widthIn(min = 280.dp)
            ) {
                items(suggestions.size) {index->
                    // Now you can call any Composable function (like Text) here.
                    Text(text = suggestions[index])

                    // Or your reusable item:
                    // SuggestionItem(
                    //     text = suggestion,
                    //     onClick = { onSuggestionClick(suggestion) }
                    // )
                }
            }
        }
    }
}