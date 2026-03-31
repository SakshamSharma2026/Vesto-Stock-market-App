package com.feature.auth.data.di

import com.feature.auth.data.AuthManager
import com.feature.auth.data.repo.AuthRepositoryImpl
import com.feature.auth.domain.repo.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()




    @Singleton
    @Provides
    fun provideAuthRepo(
        authManager: AuthManager,
    ): AuthRepository {
        return AuthRepositoryImpl(authManager)
    }

}