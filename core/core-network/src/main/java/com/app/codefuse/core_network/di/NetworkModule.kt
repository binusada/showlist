package com.app.codefuse.core_network.di

import com.app.codefuse.core_network.client.RetrofitProvider
import com.app.codefuse.core_network.config.NetworkConfig.BASE_URL
import com.app.codefuse.core_network.serialiser.JsonProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideJson(): Json = JsonProvider.json

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        RetrofitProvider(okHttpClient, json)
            .create(BASE_URL)
}