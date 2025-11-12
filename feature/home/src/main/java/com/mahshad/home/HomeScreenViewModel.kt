package com.mahshad.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.ui.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {
    val feedState: StateFlow<NewsFeed> = articleRepository
        .getNews()
        .map { result ->
            result.fold(
                onSuccess = { NewsFeed.Successful(it) },
                onFailure = { NewsFeed.Error(it) }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NewsFeed.Loading
        )
}