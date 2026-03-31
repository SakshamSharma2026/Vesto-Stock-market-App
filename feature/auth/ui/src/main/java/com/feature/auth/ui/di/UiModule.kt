package com.feature.auth.ui.di

import com.feature.auth.ui.navigation.AuthApi
import com.feature.auth.ui.navigation.AuthApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return AuthApiImpl()
    }

}