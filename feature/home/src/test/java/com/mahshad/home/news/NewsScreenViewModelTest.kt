package com.mahshad.home.news

import app.cash.turbine.test
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.domain.ArticleFeedState
import com.mahshad.domain.GetAllTheNewsUseCase
import com.mahshad.model.Article
import com.mahshad.model.FavoriteArticle
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NewsScreenViewModelTest {
    @MockK
    private lateinit var getAllTheNewsUseCase: GetAllTheNewsUseCase

    @MockK
    private lateinit var favoriteArticleRepository: FavoriteArticleRepository
    private lateinit var newsScreenViewModel: NewsScreenViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery {
            getAllTheNewsUseCase.wsjNews
        } returns MutableStateFlow(ArticleFeedState.Success(listOf(Article.DEFAULT)))
            .asStateFlow()
        coEvery {
            getAllTheNewsUseCase.teslaNews
        } returns MutableStateFlow(ArticleFeedState.Success(listOf(Article.DEFAULT)))
            .asStateFlow()
        coEvery {
            getAllTheNewsUseCase.appleNews
        } returns MutableStateFlow(ArticleFeedState.Success(listOf(Article.DEFAULT)))
            .asStateFlow()
        coEvery {
            getAllTheNewsUseCase.worldNews
        } returns MutableStateFlow(ArticleFeedState.Success(listOf(Article.DEFAULT)))
            .asStateFlow()
        coEvery {
            getAllTheNewsUseCase.techCrunchNews
        } returns MutableStateFlow(ArticleFeedState.Success(listOf(Article.DEFAULT)))
            .asStateFlow()
        coEvery { favoriteArticleRepository.getArticles() } returns flowOf(listOf(FavoriteArticle.DEFAULT))
        newsScreenViewModel = NewsScreenViewModel(getAllTheNewsUseCase, favoriteArticleRepository)
    }

    @Test
    fun `updateSearchQueryFlow_queryChanges_emitsUpdatedQuery`() = runTest {
        // Then
        newsScreenViewModel._searchQueryStateFlow.test {
            assertEquals("", awaitItem())
            // When
            newsScreenViewModel.updateSearchQueryFlow("apple watch")
            // Then
            assertEquals("apple watch", awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
    }


}