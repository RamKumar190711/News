package com.toqsoft.news.dataLayer.repository

import com.toqsoft.news.dataLayer.models.NewsArticle
import com.toqsoft.news.dataLayer.api.NewsApiService
import com.toqsoft.news.dataLayer.dao.NewsDao
import com.toqsoft.news.dataLayer.models.NewsArticleDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.toqsoft.news.dataLayer.models.toEntity

class NewsRepository @Inject constructor(
    private val api: NewsApiService,
    private val dao: NewsDao
) {
    fun getNews(country: String, apiKey: String): Flow<List<NewsArticle>> = flow {
        try {
            val response = api.getTopHeadlines(country, apiKey)
            dao.clearArticles()
            dao.insertArticles(response.articles.map { it.toEntity() })
        } catch (e: Exception) {
            // fallback to cached data
        }
        emitAll(dao.getAllArticles())
    }
}
