package com.app.codefuse.data_userprofile.di

import com.app.codefuse.data_userprofile.api.UserProfileApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@Singleton
object UserApiModule {

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserProfileApi {
        return retrofit.create(UserProfileApi::class.java)
    }
}