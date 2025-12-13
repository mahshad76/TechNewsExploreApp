package com.mahshad.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.model.Article
import kotlinx.serialization.Serializable

@Serializable
data class DetailScreenRoute(val subject: String)

@Composable
fun DetailScreen(subject: String, viewModel: HomeScreenViewModel) {
    val matchedArticles = viewModel.searchSuggestions.collectAsStateWithLifecycle()
    val articles = matchedArticles.value
    LazyVerticalGrid(
        columns = Adaptive(300.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(articles.size) {
            DetailCard(articles[it], { article: Article ->
                viewModel.bookmarkClicked(article)
            })
        }
    }
}

@Composable
fun DetailCard(article: Article, onBookMarkClicked: (Article) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Text(
                    article.title,
                    modifier = Modifier.fillMaxWidth((.8f)),
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .weight(0.2F)
                        .fillMaxHeight(1.0F)
                        .clickable(true, onClick = { onBookMarkClicked(article) }),
                    painter = painterResource(id = R.drawable.bookmark_svgrepo_com),
                    colorFilter = ColorFilter.tint(
                        color = if (article.isLiked) Color.Red
                        else Color.Black
                    ),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8F),
                text = article.content,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
@Preview
fun Previeww() {
    DetailCard(
        article = Article.DEFAULT, {}
    )
}