package com.mahshad.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.model.Article
import com.mahshad.model.FavoriteArticle
import com.mahshad.ui.NewsFeed
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
    val appleNewsFlow: Flow<NewsFeed> = articleRepository.getAppleOrTeslaNews("apple")
        .mapToNewsFeed()
    val teslaNewsFlow: Flow<NewsFeed> = articleRepository.getAppleOrTeslaNews("tesla")
        .mapToNewsFeed()
    val worldNewsFlow: Flow<NewsFeed> = articleRepository.getWorldNews("us")
        .mapToNewsFeed()
    val techCrunchFlow: Flow<NewsFeed> = articleRepository.getTechCrunchNews("techcrunch")
        .mapToNewsFeed()
    val wsjFlow: Flow<NewsFeed> = articleRepository.getWsjNews("wsj.com")
        .mapToNewsFeed()

    val mergedFlow: StateFlow<NewsFeed> =
        merge(appleNewsFlow, teslaNewsFlow, worldNewsFlow, techCrunchFlow, wsjFlow)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NewsFeed.Loading
            )

    val searchSuggestions: StateFlow<List<Article>> =
        combine(mergedFlow, _searchQueryStateFlow, _favoriteArticlesStateFlow)
        { newsFeed, query, favoriteArticles ->
            if (newsFeed is NewsFeed.Successful) {
                return@combine newsFeed.news
                    .filter { query in it.content || query in it.title }
                    .map {
                        if (it.title in favoriteArticles.map { it.title })
                            it.copy(isLiked = true) else it
                    }
            } else {
                return@combine emptyList()
            }
        }
            .debounce(300L)
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
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
                NewsFeed.Successful(it)
            },
            onFailure = {
                Log.d("TAG", "network call")
                NewsFeed.Error(it)
            }
        )
    }