package com.toqsoft.news.domainLayer

import com.toqsoft.news.dataLayer.models.NewsArticle

data class NewsUiState(
    val news: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
