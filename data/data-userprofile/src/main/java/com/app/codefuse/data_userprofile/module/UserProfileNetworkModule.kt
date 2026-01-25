package com.app.codefuse.data_userprofile.module

import com.app.codefuse.data_userprofile.api.UserProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserProfileNetworkModule {

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserProfileApi {
        return retrofit.create(UserProfileApi::class.java)
    }
}