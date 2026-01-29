package com.app.codefuse.data_userprofile.di

import com.app.codefuse.core_domain.repository.UserProfileRepository
import com.app.codefuse.data_userprofile.api.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserProfileNetworkModule {

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        impl: UserRepositoryImpl
    ): UserProfileRepository
}