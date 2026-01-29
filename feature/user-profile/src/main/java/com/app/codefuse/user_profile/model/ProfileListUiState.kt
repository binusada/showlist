package com.app.codefuse.user_profile.model

import com.app.codefuse.core_domain.models.User

sealed class ProfileListUiState {
    object Loading : ProfileListUiState()
    data class Success(val profiles: List<User>) : ProfileListUiState()
    data class Error(val message: String) : ProfileListUiState()
}