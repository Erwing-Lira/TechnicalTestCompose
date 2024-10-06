package com.example.technicaltest.di

import com.example.technicaltest.home.repository.IMovementsRepository
import com.example.technicaltest.home.repository.IUserRepository
import com.example.technicaltest.home.repository.MovementsRepositoryImpl
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
    fun provideUSerRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): IUserRepository {
        return userRepositoryImpl
    }
}