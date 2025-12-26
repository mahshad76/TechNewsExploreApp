package com.mahshad.data.repository

import com.mahshad.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getAppleOrTeslaNews(q: String): Flow<Result<List<Article>>>
    fun getWorldNews(country: String): Flow<Result<List<Article>>>
    fun getTechCrunchNews(source: String): Flow<Result<List<Article>>>
    fun getWsjNews(domains: String): Flow<Result<List<Article>>>
}