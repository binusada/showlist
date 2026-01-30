package com.app.codefuse.user_profile.ui.details

import com.app.codefuse.core_domain.models.User
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.codefuse.core_domain.repository.UserProfileRepository
import com.app.codefuse.user_profile.navigation.USER_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val userRepository: UserProfileRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        val userId: String? = savedStateHandle[USER_ID_ARG]
        if (userId != null) {
            fetchUserById(userId)
        }
    }

    private fun fetchUserById(userId: String) {
        viewModelScope.launch {
            try {
                val userList = userRepository.fetchProfiles(50) // Or from a local cache
                _user.value = userList.find { it.id == userId }
            } catch (e: Exception) {
                _user.value = null
            }
        }
    }
}
