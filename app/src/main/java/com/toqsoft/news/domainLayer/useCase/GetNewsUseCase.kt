package com.toqsoft.news.domainLayer.useCase

import com.toqsoft.news.dataLayer.model.NewsArticle
import com.toqsoft.news.dataLayer.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(country: String, apiKey: String): Flow<List<NewsArticle>> =
        repository.getNews(country, apiKey)
}
