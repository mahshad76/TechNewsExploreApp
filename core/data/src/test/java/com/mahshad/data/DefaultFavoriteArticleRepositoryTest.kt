package com.mahshad.data

import app.cash.turbine.test
import com.mahshad.data.repository.DefaultFavoriteArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.database.FavoriteArticleEntity
import com.mahshad.database.toFavoriteArticle
import com.mahshad.datasource.localdatasource.ArticleDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultFavoriteArticleRepositoryTest {
    private lateinit var favoriteArticleRepository: FavoriteArticleRepository

    @MockK
    private lateinit var articleDataSource: ArticleDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        favoriteArticleRepository = DefaultFavoriteArticleRepository(articleDataSource)
    }

    @Test
    fun `getArticles_whenFavoritesExist_emitsConvertedArticles`() = runTest {
        // Given
        coEvery { articleDataSource.getArticles() } returns flow {
            emit(listOf(FavoriteArticleEntity.DEFAULT))
        }
        // When
        val articles = favoriteArticleRepository.getArticles()
        //Then
        articles.test {
            assertEquals(
                listOf(FavoriteArticleEntity.DEFAULT.toFavoriteArticle()),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
    }

}