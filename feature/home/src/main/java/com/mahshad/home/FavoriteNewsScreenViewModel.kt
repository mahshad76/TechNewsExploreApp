package com.mahshad.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class FavoriteNewsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
    private val favoriteArticleRepository: FavoriteArticleRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    lateinit var b: MutableList<Flow<List<String>>>
    val a = userDataRepository.getUserData().flatMapLatest { favoriteTopics: Set<String> ->
        val a: Flow<String> = favoriteTopics.asFlow()
        a.flatMapMerge { string -> flowOf(string) }
    }
}