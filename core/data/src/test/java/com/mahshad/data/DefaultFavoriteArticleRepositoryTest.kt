package com.mahshad.data

import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.datasource.localdatasource.ArticleDataSource
import org.junit.After
import org.junit.Before
import org.junit.Test

class DefaultFavoriteArticleRepositoryTest {
    private lateinit var favoriteArticleRepository: FavoriteArticleRepository
    @Mockk
    private lateinit var articleDataSource: ArticleDataSource

    @Before
    fun setUp() {

    }

    @Test

    @After
    fun tearDown() {
    }

}