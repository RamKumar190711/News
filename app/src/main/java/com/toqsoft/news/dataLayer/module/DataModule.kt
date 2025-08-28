    package com.toqsoft.news.dataLayer.module

    import android.content.Context
    import androidx.room.Room
    import com.toqsoft.news.dataLayer.api.NewsApiService
    import com.toqsoft.news.dataLayer.dao.NewsDao
    import com.toqsoft.news.dataLayer.database.NewsDatabase
    import com.toqsoft.news.dataLayer.repository.NewsRepository
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import okhttp3.OkHttpClient
    import okhttp3.logging.HttpLoggingInterceptor
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import javax.inject.Singleton

    import dagger.hilt.android.qualifiers.ApplicationContext

    @Module
    @InstallIn(SingletonComponent::class)
    object DataModule {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext appContext: Context): NewsDatabase =
            Room.databaseBuilder(appContext, NewsDatabase::class.java, "news_db").build()

        @Provides
        fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao()

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        @Provides
        @Singleton
        fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Provides
        @Singleton
        fun provideNewsApi(retrofit: Retrofit): NewsApiService =
            retrofit.create(NewsApiService::class.java)

        @Provides
        @Singleton
        fun provideRepository(api: NewsApiService, dao: NewsDao): NewsRepository =
            NewsRepository(api, dao)
    }

