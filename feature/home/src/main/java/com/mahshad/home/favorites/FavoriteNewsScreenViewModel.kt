package com.mahshad.home.favorites

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.data.repository.UserDataRepository
import com.mahshad.model.Article
import com.mahshad.ui.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNewsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
    private val favoriteArticleRepository: FavoriteArticleRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    private val _searchQueryStateFlow: MutableStateFlow<String> =
        savedStateHandle.getMutableStateFlow(
            "search_query_key",
            ""
        )
    val searchQueryStateFlow = _searchQueryStateFlow.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchSuggestions: StateFlow<NewsFeed<List<Article>>> =
        userDataRepository.getUserData().flatMapLatest { favoriteTopics: Set<String> ->
            val flowOfTopics: Flow<String> = favoriteTopics.asFlow()
            val allFavoriteNewsFlow = flowOfTopics.flatMapMerge { topic: String ->
                when (topic) {
                    "Apple articles" -> articleRepository.getAppleOrTeslaNews("apple")
                    "US headlines" -> articleRepository.getWorldNews("us")
                    "Tech crunch" -> articleRepository.getTechCrunchNews("techcrunch")
                    "Wall Street Journal" -> articleRepository.getWsjNews("wsj.com")
                    "Tesla articles" -> articleRepository.getAppleOrTeslaNews("tesla")
                    else -> flowOf(Result.success(emptyList()))
                }
            }
            combine(
                allFavoriteNewsFlow, favoriteArticleRepository.getArticles(),
                _searchQueryStateFlow
            ) { a, b, query ->
                if (a.isSuccess) {
                    val res = a.getOrNull()
                        ?.filter {
                            query.lowercase() in it.content.lowercase() ||
                                    query in it.title.lowercase() || query.isEmpty()
                        }
                        ?.map {
                            if (it.title in b.map { it.title }) it.copy(isLiked = true) else it
                        }
                    if (res != null) NewsFeed.Successful(res) else NewsFeed.Successful(emptyList())
                } else {
                    val exception = a.exceptionOrNull()
                    if (exception != null) NewsFeed.Error(a.exceptionOrNull()!!) else NewsFeed.Error(
                        Throwable("unknown error on the server side")
                    )
                }
            }
        }.stateIn(
            viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5_000),
            NewsFeed.Loading
        )

    fun updateSearchStateFlow(update: String) {
        _searchQueryStateFlow.value = update
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