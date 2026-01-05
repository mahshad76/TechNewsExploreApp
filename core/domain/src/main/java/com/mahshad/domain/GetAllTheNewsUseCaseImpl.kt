package com.mahshad.domain

import com.mahshad.data.repository.ArticleRepository
import com.mahshad.model.Article
import com.mahshad.threading.common.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetAllTheNewsUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    @ApplicationScope private val scope: CoroutineScope
) :
    GetAllTheNewsUseCase {

    override val appleNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getAppleOrTeslaNews("apple")
            .mapToArticleFeedState()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val teslaNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getAppleOrTeslaNews("tesla")
            .mapToArticleFeedState()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val worldNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getWorldNews("us")
            .mapToArticleFeedState()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val techCrunchNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getTechCrunchNews("techcrunch")
            .mapToArticleFeedState()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val wsjNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getWsjNews("wsj.com")
            .mapToArticleFeedState()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
}

fun Flow<Result<List<Article>>>.mapToArticleFeedState() =
    this.map { result ->
        result.fold(
            onSuccess = {
                ArticleFeedState.Success(it)
            },
            onFailure = {
                ArticleFeedState.Error(it)
            }
        )
    }