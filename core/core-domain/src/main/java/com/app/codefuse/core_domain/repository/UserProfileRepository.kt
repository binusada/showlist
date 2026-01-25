package com.app.codefuse.core_domain.repository

import com.app.codefuse.core_domain.models.User

interface UserProfileRepository {
    suspend fun fetchProfiles(count: Int): List<User>
}