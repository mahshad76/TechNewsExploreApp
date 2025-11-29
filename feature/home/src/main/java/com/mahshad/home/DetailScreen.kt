package com.mahshad.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
            DetailCard(articles[it])
        }
    }
}

@Composable
fun DetailCard(article: Article) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        val iconTint = LocalTintTheme.current.iconTint
        Row(
            modifier = Modifier.fillMaxHeight(0.2F),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .weight(0.2F)
                    .fillMaxHeight(1.0F),
                painter = painterResource(id = R.drawable.bookmark_svgrepo_com),
                //colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null,
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .weight(0.8F)
                    .fillMaxHeight(1.0F),
                text = article.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8F),
            text = article.content,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
@Preview
fun Previeww() {
    DetailCard(
        article = Article.DEFAULT
    )
}