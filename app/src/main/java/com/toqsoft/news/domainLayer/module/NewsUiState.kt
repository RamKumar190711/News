package com.toqsoft.news.domainLayer.module

import com.toqsoft.news.dataLayer.model.NewsArticle

data class NewsUiState(
    val news: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)