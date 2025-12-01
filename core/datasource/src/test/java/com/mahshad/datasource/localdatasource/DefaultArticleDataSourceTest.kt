package com.mahshad.datasource.localdatasource

import app.cash.turbine.test
import com.mahshad.database.FavoriteArticleDao
import com.mahshad.database.FavoriteArticleEntity
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultArticleDataSourceTest {
    private lateinit var favoriteArticleDao: FavoriteArticleDao
    private lateinit var articleDataSource: ArticleDataSource
    private lateinit var testDispatcher: TestDispatcher

    @Before
    fun setUp() {
        favoriteArticleDao = FakeDao()
        testDispatcher = StandardTestDispatcher()
        articleDataSource = DefaultArticleDataSource(favoriteArticleDao, testDispatcher)
    }

    @Test
    fun `getArticles_whenTheTableIsNotEmpty_emittingTheListOfFavorites`() =
        runTest(testDispatcher) {
            // Given
            // When
            val likedArticles = articleDataSource.getArticles()
            // Then
            likedArticles.test {
                assertEquals(awaitItem(), listOf(FavoriteArticleEntity.DEFAULT))
                cancelAndIgnoreRemainingEvents()
            }
        }

    @After
    fun tearDown() {
    }
}