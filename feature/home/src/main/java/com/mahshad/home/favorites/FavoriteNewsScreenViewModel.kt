package com.mahshad.home.favorites

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.data.repository.UserDataRepository
import com.mahshad.domain.ArticleFeedState
import com.mahshad.domain.GetAllTheNewsUseCase
import com.mahshad.home.NewsFeedUiState
import com.mahshad.model.Article
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNewsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAllTheNewsUseCase: GetAllTheNewsUseCase,
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
    val searchSuggestions: StateFlow<NewsFeedUiState<List<Article>>> =
        userDataRepository.getUserData().flatMapLatest { favoriteTopics: Set<String> ->
            val flowOfTopics: Flow<String> = favoriteTopics.asFlow()
            val allFavoriteNewsFlow: Flow<NewsFeedUiState<List<Article>>> =
                flowOfTopics.flatMapMerge { topic: String ->
                    when (topic) {
                        "Apple articles" -> getAllTheNewsUseCase.appleNews
                        "US headlines" -> getAllTheNewsUseCase.worldNews
                        "Tech crunch" -> getAllTheNewsUseCase.techCrunchNews
                        "Wall Street Journal" -> getAllTheNewsUseCase.wsjNews
                        "Tesla articles" -> getAllTheNewsUseCase.teslaNews
                        else -> MutableStateFlow(ArticleFeedState.Success(emptyList())).asStateFlow()
                    }
                }.mapToNewsFeed()
            combine(
                allFavoriteNewsFlow, favoriteArticleRepository.getArticles(),
                _searchQueryStateFlow
            ) { a, b, query ->
                if (a is NewsFeedUiState.Successful) {
                    val res = a.news
                        .filter {
                            query.lowercase() in it.content.lowercase() ||
                                    query in it.title.lowercase() || query.isEmpty()
                        }
                        .map {
                            if (it.title in b.map { it.title }) it.copy(isLiked = true) else it
                        }
                    NewsFeedUiState.Successful(res)
                } else {
                    if (a is NewsFeedUiState.Error) {
                        val exception = a.e
                        NewsFeedUiState.Error(exception)
                    } else {
                        NewsFeedUiState.Loading
                    }
                }
            }
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            NewsFeedUiState.Loading
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

fun Flow<ArticleFeedState<List<Article>>>.mapToNewsFeed() =
    this.map { articleFeedState ->
        when (articleFeedState) {
            is ArticleFeedState.Success -> NewsFeedUiState.Successful(articleFeedState.articles)
            is ArticleFeedState.Error -> NewsFeedUiState.Error(articleFeedState.cause)
            is ArticleFeedState.Loading -> NewsFeedUiState.Loading
        }
    }