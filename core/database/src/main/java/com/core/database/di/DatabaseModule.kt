package com.core.database.di

import android.content.Context
import androidx.room.Room
import com.core.database.AppDatabase
import com.core.database.dao.UserDao
import com.core.database.dao.WalletDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }


    @Singleton
    @Provides
    fun provideWalletDao(database: AppDatabase): WalletDao = database.walletDao()


    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()
}