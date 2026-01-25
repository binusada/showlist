package com.app.codefuse.data_userprofile.api

import com.app.codefuse.data_userprofile.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserProfileApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("results") resultCount: Int = 50,
        @Query("page") page: Int? = null,
        @Query("seed") seed: String? = null
    ): UserResponse
}