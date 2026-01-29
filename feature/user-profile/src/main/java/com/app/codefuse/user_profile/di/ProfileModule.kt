package com.app.codefuse.user_profile.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {

   @Binds
   @Singleton
   fun bindUserProfileRepository(impl: UserProfileRepositoryImpl): UserProfileRepository



}