package com.feature.profile.ui.di

import com.feature.profile.ui.navigation.ProfileApi
import com.feature.profile.ui.navigation.ProfileApiImpl
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
    fun provideProfileApi(): ProfileApi {
        return ProfileApiImpl()
    }

}