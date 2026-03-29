package com.core.database.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideWalletDao(database: AppDatabase) = database.walletDao()
}