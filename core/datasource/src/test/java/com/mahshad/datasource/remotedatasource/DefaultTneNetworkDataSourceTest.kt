package com.mahshad.datasource.remotedatasource

import com.mahshad.Dto.NewsApiResponse
import com.mahshad.network.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DefaultTneNetworkDataSourceTest {
    private lateinit var tneNetworkDataSource: TneNetworkDataSource
    private lateinit var testDispatcher: TestDispatcher

    @MockK
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcher = StandardTestDispatcher()
        tneNetworkDataSource = DefaultTneNetworkDataSource(apiService, testDispatcher)
    }

    @Test
    fun `getNews_whenResponseIsSuccessful_emittingApiResponse`() = runTest(testDispatcher) {
        // Given
        coEvery { apiService.getNews() } returns retrofit2.Response.success(NewsApiResponse.DEFAULT)
        // When
        val response = tneNetworkDataSource.getNews()
        // Then
        assertTrue(response.isSuccessful)
        assertEquals(response.body(), NewsApiResponse.DEFAULT)
    }

    @Test
    fun `getNews_whenResponseIsServerError_emittinFailureResponse`() = runTest(testDispatcher) {
        // Given
        coEvery { apiService.getNews() } returns retrofit2.Response.error(
            404,
            "{\"error\":\"Internal Server Error\"}".toResponseBody(
                "application/json".toMediaTypeOrNull()
            )
        )
        // When
        val response = tneNetworkDataSource.getNews()
        // Then
        assertTrue(!response.isSuccessful)
    }

    @After
    fun tearDown() {
    }
}