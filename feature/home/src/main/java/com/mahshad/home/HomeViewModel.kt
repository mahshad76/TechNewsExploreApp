package com.mahshad.home

import androidx.lifecycle.ViewModel
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {
    private val _articleStateFlow: MutableStateFlow<ArticleState<List<Article>>> =
        MutableStateFlow(ArticleState.Loading)
    val articleStateFlow = _articleStateFlow.asStateFlow()

}

sealed interface ArticleState<out T> {
    data class Success(val data: List<Article>) : ArticleState<List<Article>>
    data class Error(val error: Exception) : ArticleState<Nothing>
    object Loading : ArticleState<Nothing>
}