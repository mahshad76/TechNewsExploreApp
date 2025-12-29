package com.mahshad.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mahshad.model.Article

@Composable
fun SearchSuggestionsBox(
    suggestions: List<Article>,
    onSuggestionClick: (String) -> Unit,
    icon: Painter,
    onIconClicked: (Article) -> Unit
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
                        article = suggestions[index],
                        onClick = {},
                        icon = icon,
                        onIconClicked = onIconClicked
                    )
            }
        }
    }
}

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    icon: Painter,
    onIconClicked: (Article) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(bottom = 11.73.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceBright)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "news image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(35.19.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(2.67.dp))
            )
            Spacer(modifier = Modifier.width(8.53.dp))
            Text(
                text = article.title.split(" ")
                    .take(10)
                    .joinToString(" "),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(3.0F)
            )
            Box(
                modifier = Modifier
                    .weight(1.0F)
                    .fillMaxHeight()
                    .padding(end = 7.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    icon,
                    contentDescription = "",
                    tint = if (article.isLiked) Color.Red else Color.Black,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(true) { onIconClicked(article) }
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun ArticleCardPreview() {
//    ArticleCard("news", "url", {})
//}