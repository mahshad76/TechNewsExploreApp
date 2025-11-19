package com.mahshad.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.model.Article
import kotlinx.serialization.Serializable

@Serializable
data class DetailScreenRoute(val subject: String)

@Composable
fun DetailScreen(subject: String, viewModel: HomeScreenViewModel) {
    val matchedArticles = viewModel.searchSuggestions.collectAsStateWithLifecycle()
    val articles = matchedArticles.value
    List(articles)
}

@Composable
fun List(articles: List<Article>) {
    LazyColumn {
        items(articles.size) {
            Text(articles[it].toString())
        }
    }
}