package com.mahshad.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun SearchSuggestionsBox(
    suggestions: List<Pair<String, String>>,
    onSuggestionClick: (String) -> Unit
) {
    if (suggestions.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
            //.background(Color.LightGray)
        ) {
            items(suggestions.size) { index ->
                val article =
                    ArticleCard(
                        title = suggestions[index].first,
                        url = suggestions[index].second,
                        onClick = { onSuggestionClick(suggestions[index].first) }
                    )
            }
        }
    }
}

@Composable
fun ArticleCard(
    title: String,
    url: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceBright)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = url,
                contentDescription = "",
                modifier = Modifier
                    .size(35.19.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(2.67.dp))
            )
            Spacer(modifier = Modifier.width(8.53.dp))
            Text(
                text = title.split(" ")
                    .take(10)
                    .joinToString(" "),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun ArticleCardPreview() {
    ArticleCard("news", "url", {})
}