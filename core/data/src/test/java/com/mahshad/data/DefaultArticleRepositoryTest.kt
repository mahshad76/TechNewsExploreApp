package com.mahshad.data

import app.cash.turbine.test
import com.mahshad.Dto.NewsApiResponse
import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.DefaultArticleRepository
import com.mahshad.datasource.remotedatasource.TneNetworkDataSource
import com.mahshad.model.Article
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DefaultArticleRepositoryTest {
    @MockK
    private lateinit var tneNetworkDataSource: TneNetworkDataSource
    private lateinit var articleRepository: ArticleRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        articleRepository = DefaultArticleRepository(tneNetworkDataSource)
    }

    @Test
    fun `getNews_whenResponseIsSuccessful_emittingSuccessfulConvertedResult`() = runTest {
        // Given
        coEvery { tneNetworkDataSource.getNews() } returns Response.success(
            NewsApiResponse.DEFAULT
        )
        // When
        val news = articleRepository.getNews()
        // Then
        news.test {
            assertEquals(Result.success(listOf(Article.DEFAULT)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getNews_whenResponseIsFailure_emittingUnSuccessResult`() = runTest {
        // Given
        coEvery { tneNetworkDataSource.getNews() } returns retrofit2.Response.error(
            404,
            "{\"error\":\"Internal Server Error\"}".toResponseBody(
                "application/json".toMediaTypeOrNull()
            )
        )
        // When
        val news = articleRepository.getNews()
        // Then
        news.test {
            assertTrue(awaitItem().isFailure)
            cancelAndIgnoreRemainingEvents()
        }
    }

}