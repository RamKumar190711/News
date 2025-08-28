package com.toqsoft.news.domainLayer.module

import com.toqsoft.news.dataLayer.repository.NewsRepository
import com.toqsoft.news.domainLayer.useCase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetNewsUseCase(repository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCase(repository)
    }
}
