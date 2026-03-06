package com.core.network.di

import android.content.Context
import com.core.network.BuildConfig
import com.core.network.connectivity.ConnectivityObserver
import com.core.network.connectivity.NetworkConnectivityObserver
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun providesConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}
