package com.toqsoft.news.dataLayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toqsoft.news.dataLayer.dao.NewsDao
import com.toqsoft.news.dataLayer.model.NewsArticle

@Database(entities = [NewsArticle::class], version = 1,exportSchema = false )
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
