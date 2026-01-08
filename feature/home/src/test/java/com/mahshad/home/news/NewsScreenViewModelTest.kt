package com.mahshad.home.news

import app.cash.turbine.test
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.domain.ArticleFeedState
import com.mahshad.domain.GetAllTheNewsUseCase
import com.mahshad.model.Article
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        listOf(
            getAllTheNewsUseCase.wsjNews,
            getAllTheNewsUseCase.teslaNews,
            getAllTheNewsUseCase.appleNews,
            getAllTheNewsUseCase.worldNews,
            getAllTheNewsUseCase.techCrunchNews
        ).forEach { newsFlow ->
            every { newsFlow } returns
                    MutableStateFlow(
                        ArticleFeedState.Success(
                            listOf(Article.DEFAULT)
                        )
                    ).asStateFlow()
        }
    }

    @Test
    fun `updateSearchQueryFlow_queryChanges_emitsUpdatedQuery`() = runTest {
        newsScreenViewModel._searchQueryStateFlow.test {
            // Then
            assertEquals("", awaitItem())
            // When
            newsScreenViewModel.updateSearchQueryFlow("apple watch")
            // Then
            assertEquals("apple watch", awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `searchSuggestions_withFavorites_emitsSuccessWithFavoriteMarkers`() = runTest {
        // Given
        newsScreenViewModel.updateSearchQueryFlow("apple watch")
        newsScreenViewModel.searchSuggestions.test {

        }
    }

    @After
    fun tearDown() {
    }
}