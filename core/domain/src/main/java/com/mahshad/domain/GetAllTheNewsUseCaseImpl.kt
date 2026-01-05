package com.mahshad.domain

import com.mahshad.data.repository.ArticleRepository
import javax.inject.Inject
import javax.inject.Singleton

class GetAllTheNewsUseCaseImpl @Inject constructor(private val articleRepository: ArticleRepository) {

}