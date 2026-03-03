package com.saksham.sharma.vesto.core.di

import com.saksham.sharma.vesto.BuildConfig
import com.saksham.sharma.vesto.core.network.ApiService
import com.saksham.sharma.vesto.core.network.ConnectivityObserver
import com.saksham.sharma.vesto.core.network.NetworkConnectivityObserver
import com.saksham.sharma.vesto.features.data.repository.StockRepositoryImpl
import com.saksham.sharma.vesto.features.domain.repository.StockRepository
import com.saksham.sharma.vesto.features.domain.usecases.StockUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class RetrofitInstance {
    val intercepter = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(intercepter)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)

    }.build()
}


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(RetrofitInstance().client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesStockRepo(apiService: ApiService): StockRepository {
        return StockRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesStockUseCases(stockRepository: StockRepository): StockUseCases {
        return StockUseCases(stockRepository)
    }

    @Provides
    @Singleton
    fun providesConnectivityObserver(
        @dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}
