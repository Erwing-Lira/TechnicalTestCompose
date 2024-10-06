package com.example.technicaltest.di

import com.example.technicaltest.signup.repository.ISignUpRegistry
import com.example.technicaltest.signup.repository.SignUpRegistryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {
    @Provides
    @Singleton
    fun provideSignUpRepository(
        signUpRegistryImpl: SignUpRegistryImpl
    ): ISignUpRegistry {
        return signUpRegistryImpl
    }
}