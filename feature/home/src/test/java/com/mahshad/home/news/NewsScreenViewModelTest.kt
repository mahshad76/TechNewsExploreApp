package com.mahshad.home.news

import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.domain.GetAllTheNewsUseCase
import io.mockk.impl.annotations.MockK

class NewsScreenViewModelTest {
    @MockK
    private lateinit var getAllTheNewsUseCase: GetAllTheNewsUseCase

    @MockK
    private lateinit var favoriteArticleRepository: FavoriteArticleRepository
    private lateinit var newsScreenViewModel: NewsScreenViewModel


}