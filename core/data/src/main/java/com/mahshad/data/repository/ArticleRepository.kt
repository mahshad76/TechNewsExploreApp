package com.mahshad.data.repository

import com.mahshad.model.Article

interface ArticleRepository {
    suspend fun getNews(): Result<List<Article>>
}