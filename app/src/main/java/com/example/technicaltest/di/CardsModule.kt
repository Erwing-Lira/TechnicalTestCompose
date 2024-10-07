package com.example.technicaltest.di

import com.example.technicaltest.home.repository.CardsRepositoryImpl
import com.example.technicaltest.home.repository.ICardsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CardsModule {
    @Provides
    @Singleton
    fun provideCardsRepository(
        cardsRepositoryImpl: CardsRepositoryImpl
    ): ICardsRepository {
        return cardsRepositoryImpl
    }
}