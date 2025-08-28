package com.toqsoft.news.dataLayer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Room entity
@Entity(tableName = "news_articles")
data class NewsArticle(
    @PrimaryKey val url: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val publishedAt: String,
    val sourceName: String
)

// DTO from API
data class NewsArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    @SerializedName("urlToImage") val imageUrl: String?,
    val publishedAt: String,
    val content: String?
)

// Nested DTO
data class SourceDto(
    val id: String?,
    val name: String
)
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticleDto>   // must be NewsArticleDto
)



// Mapper
fun NewsArticleDto.toEntity(): NewsArticle {
    return NewsArticle(
        url = url,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        sourceName = source.name
    )
}
