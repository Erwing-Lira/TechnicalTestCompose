package com.example.technicaltest.di

import com.example.technicaltest.home.repository.IRegistryCardRepository
import com.example.technicaltest.home.repository.RegistryCardRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistryCardModule {
    @Provides
    @Singleton
    fun provideRegistryCardRepository(
        registryCardRepositoryImpl: RegistryCardRepositoryImpl
    ): IRegistryCardRepository {
        return registryCardRepositoryImpl
    }
}