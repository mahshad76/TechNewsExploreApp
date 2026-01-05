package com.mahshad.home.news

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.home.NewsFeedUiState
import com.mahshad.model.Article
import com.mahshad.model.FavoriteArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
    private val favoriteArticleRepository: FavoriteArticleRepository
) : ViewModel() {
    private val searchQueryStateFlow = savedStateHandle.getMutableStateFlow(
        "search_query_key",
        ""
    )
    val _searchQueryStateFlow = searchQueryStateFlow.asStateFlow()

    private val _favoriteArticlesStateFlow: StateFlow<List<FavoriteArticle>> =
        favoriteArticleRepository.getArticles().stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            emptyList<FavoriteArticle>()
        )
    val appleNewsFlow: Flow<NewsFeedUiState<List<Article>>> =
        articleRepository.getAppleOrTeslaNews("apple")
            .mapToNewsFeed()
    val teslaNewsFlow: Flow<NewsFeedUiState<List<Article>>> =
        articleRepository.getAppleOrTeslaNews("tesla")
            .mapToNewsFeed()
    val worldNewsFlow: Flow<NewsFeedUiState<List<Article>>> = articleRepository.getWorldNews("us")
        .mapToNewsFeed()
    val techCrunchFlow: Flow<NewsFeedUiState<List<Article>>> =
        articleRepository.getTechCrunchNews("techcrunch")
            .mapToNewsFeed()
    val wsjFlow: Flow<NewsFeedUiState<List<Article>>> = articleRepository.getWsjNews("wsj.com")
        .mapToNewsFeed()

    val mergedFlow: StateFlow<NewsFeedUiState<List<Article>>> =
        merge(appleNewsFlow, teslaNewsFlow, worldNewsFlow, techCrunchFlow, wsjFlow)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NewsFeedUiState.Loading
            )

    val searchSuggestions: StateFlow<NewsFeedUiState<List<Article>>> =
        combine(mergedFlow, _searchQueryStateFlow, _favoriteArticlesStateFlow)
        { newsFeed, query, favoriteArticles ->
            Log.d("TAG", "okay")
            if (newsFeed is NewsFeedUiState.Successful) {
                val articleList = newsFeed.news
                    .filter {
                        query.lowercase() in it.content.lowercase() ||
                                query.lowercase() in it.title.lowercase() || query.isEmpty()
                    }
                    .map {
                        if (it.title in favoriteArticles.map { it.title })
                            it.copy(isLiked = true) else it
                    }
                return@combine NewsFeedUiState.Successful(articleList)
            } else {
                Log.d("TAG", "problem")
                return@combine newsFeed
            }
        }
            .debounce(300L)
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NewsFeedUiState.Loading
            )

    fun updateSearchQueryFlow(query: String) {
        searchQueryStateFlow.update { query }
    }

    fun bookmarkClicked(article: Article) {
        viewModelScope.launch {
            val updatedArticle = article.copy(isLiked = !article.isLiked)
            if (updatedArticle.isLiked) {
                favoriteArticleRepository.insert(article = article)
            } else {
                favoriteArticleRepository.delete(article.title, article.author)
            }
        }
    }
}

fun Flow<Result<List<Article>>>.mapToNewsFeed() =
    this.map { result ->
        result.fold(
            onSuccess = {
                Log.d("TAG", "network call")
                NewsFeedUiState.Successful(it)
            },
            onFailure = {
                Log.d("TAG", "network call")
                NewsFeedUiState.Error(it)
            }
        )
    }