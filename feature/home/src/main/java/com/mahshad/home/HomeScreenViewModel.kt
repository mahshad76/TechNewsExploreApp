package com.mahshad.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.ui.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {
    private val searchFlow: MutableStateFlow<String> =
        MutableStateFlow("")
    val feedState: StateFlow<NewsFeed> = articleRepository
        .getNews()
        .map { result ->
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
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NewsFeed.Loading
        )

    suspend fun updateSearchFlow(query: String) {
        searchFlow.emit(query)
    }

}