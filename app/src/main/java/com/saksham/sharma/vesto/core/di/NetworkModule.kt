package com.saksham.sharma.vesto.core.di

import android.content.Context
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        //build client
        return OkHttpClient.Builder()

            //create anonymous interceptor in the lambda and override intercept
            // passing in Interceptor.Chain parameter
            .addInterceptor { chain ->

                //return response
                chain.proceed(
                    //create request
                    chain.request()
                        .newBuilder()
                        //add headers to the request builder
                        .also {
                            it.addHeader("x-api-key", BuildConfig.API_KEY)
                        }.build()

                )
            }
            //add timeouts, logging
            .also { okHttpClient ->

                okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
                okHttpClient.readTimeout(30, TimeUnit.SECONDS)
                //log if in debugging phase
                if (BuildConfig.DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {

                        level = HttpLoggingInterceptor.Level.BODY
                    }

                    okHttpClient.addInterceptor(httpLoggingInterceptor)
                }
            }
            .build()


    }


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient())
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
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}
