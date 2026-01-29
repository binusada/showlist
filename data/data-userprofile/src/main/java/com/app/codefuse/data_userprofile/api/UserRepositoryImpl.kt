package com.app.codefuse.data_userprofile.api

import com.app.codefuse.core_domain.models.User
import com.app.codefuse.core_domain.repository.UserProfileRepository
import com.app.codefuse.data_userprofile.mapper.toDomain
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val apiService: UserProfileApi
) : UserProfileRepository {

    override suspend fun fetchProfiles(count: Int): List<User> {
        val response = apiService.getUsers(resultCount = count)
        return response.results.map { it.toDomain() }
    }
}