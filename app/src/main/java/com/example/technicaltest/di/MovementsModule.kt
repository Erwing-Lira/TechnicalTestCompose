package com.example.technicaltest.di

import com.example.technicaltest.home.repository.IMovementsRepository
import com.example.technicaltest.home.repository.MovementsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovementsModule {
    @Provides
    @Singleton
    fun provideMovementsRepository(
        movementsRepositoryImpl: MovementsRepositoryImpl
    ): IMovementsRepository {
        return movementsRepositoryImpl
    }
}