package com.example.technicaltest.di

import com.example.technicaltest.home.repository.ILogOutRepository
import com.example.technicaltest.home.repository.LogOutRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LogOutModule {
    @Provides
    @Singleton
    fun provideLogOutRepository(
        logOutRepositoryImpl: LogOutRepositoryImpl
    ): ILogOutRepository {
        return logOutRepositoryImpl
    }
}