package com.example.technicaltest.di

import com.example.technicaltest.signin.repository.AuthenticateRepositoryImpl
import com.example.technicaltest.signin.repository.IAuthenticateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignInModule {
    @Provides
    @Singleton
    fun provideAuthenticateRepository(
        authenticateRepositoryImpl: AuthenticateRepositoryImpl
    ): IAuthenticateRepository {
        return authenticateRepositoryImpl
    }
}