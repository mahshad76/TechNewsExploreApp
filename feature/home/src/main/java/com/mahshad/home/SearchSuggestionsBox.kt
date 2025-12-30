package com.mahshad.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mahshad.model.Article
import com.mahshad.ui.icons.TneIcons.Bookmark
import com.mahshad.ui.icons.TneIcons.BookmarkFilled

@Composable
fun SearchSuggestionsBox(
    suggestions: List<Article>,
    onSuggestionClick: (String) -> Unit,
    onIconClicked: (Article) -> Unit
) {
    if (suggestions.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 11.73.dp)
            //.background(Color.LightGray)
        ) {
            items(suggestions.size) { index ->
                val article =
                    ArticleCard(
                        article = suggestions[index],
                        onClick = {},
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
    onIconClicked: (Article) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 11.73.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "news image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(136.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 2.67.dp,
                            topEnd = 2.67.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
            )
            Row(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = article.title.split(" ")
                        .take(10)
                        .joinToString(" "),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(3.0F)
                )
                Box(
                    modifier = Modifier
                        .weight(1.0F)
                        .fillMaxHeight()
                        .padding(end = 7.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    val icon = if (article.isLiked) painterResource(BookmarkFilled) else
                        painterResource(Bookmark)
                    Icon(
                        icon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(true) { onIconClicked(article) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(article.description)
        }
    }
}

//@Preview
//@Composable
//fun ArticleCardPreview() {
//    ArticleCard("news", "url", {})
//}