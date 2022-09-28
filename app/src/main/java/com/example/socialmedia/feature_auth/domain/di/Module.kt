package com.example.socialmedia.feature_auth.domain.di


import com.example.socialmedia.feature_auth.domain.use_case.ValidateEmail
import com.example.socialmedia.feature_auth.domain.use_case.ValidatePassword
import com.example.socialmedia.feature_auth.domain.use_case.ValidateUsername
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideValidateEmail() = ValidateEmail()

    @Singleton
    @Provides
    fun provideValidatePassword() = ValidatePassword()

    @Singleton
    @Provides
    fun provideValidateUsername() = ValidateUsername()

}