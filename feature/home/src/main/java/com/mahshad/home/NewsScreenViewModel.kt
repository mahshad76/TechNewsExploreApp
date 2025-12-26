package com.mahshad.home

import androidx.lifecycle.ViewModel
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val favoriteArticleRepository: FavoriteArticleRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

}