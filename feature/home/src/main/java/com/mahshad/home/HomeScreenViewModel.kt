package com.mahshad.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.ui.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository
) :
    ViewModel() {
    private val searchQueryStateFlow = savedStateHandle.getMutableStateFlow(
        "search_query_key",
        ""
    )
    val _searchQueryStateFlow = searchQueryStateFlow.asStateFlow()
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

    fun updateSearchQueryFlow(query: String) {
        searchQueryStateFlow.update { query }
    }

//    suspend fun updateSearchResults(): StateFlow<NewsFeed> {
//        return feedState.combine(_searchQueryStateFlow) { newsFeed, query ->
//
//        }
//            .debounce(300L)
//            .distinctUntilChanged()
//            .stateIn(
//                scope = viewModelScope,
//                //started = NewsFeed.Loading,
//                //initialValue = SearchState.Empty()
//            )
//    }
}