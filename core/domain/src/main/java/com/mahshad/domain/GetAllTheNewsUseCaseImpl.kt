package com.mahshad.domain

import android.util.Log
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetAllTheNewsUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val scope: CoroutineScope
) :
    GetAllTheNewsUseCase {

    override val appleNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getAppleOrTeslaNews("apple")
            .mapToArticleFeedState()
            .catch { e ->
                Log.d("TAG", "error in apple ${e.message}")
            }
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val teslaNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getAppleOrTeslaNews("tesla")
            .mapToArticleFeedState()
            .catch { e ->
                Log.d("TAG", "error in tesla ${e.message}")
            }
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val worldNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getWorldNews("us")
            .mapToArticleFeedState()
            .catch { e ->
                Log.d("TAG", "error in us ${e.message}")
            }
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val techCrunchNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getTechCrunchNews("techcrunch")
            .mapToArticleFeedState()
            .catch { e ->
                Log.d("TAG", "error in techcrunch ${e.message}")
            }
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                ArticleFeedState.Loading
            )
    override val wsjNews: StateFlow<ArticleFeedState<List<Article>>> =
        articleRepository.getWsjNews("wsj.com")
            .mapToArticleFeedState()
            .catch { e ->
                Log.d("TAG", "error in wsj ${e.message}")
            }
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