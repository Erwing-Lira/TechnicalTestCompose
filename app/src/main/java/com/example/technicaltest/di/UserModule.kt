package com.example.technicaltest.di

import com.example.technicaltest.home.repository.IUserRepository
import com.example.technicaltest.home.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): IUserRepository {
        return userRepositoryImpl
    }
}