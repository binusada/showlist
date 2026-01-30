package com.app.codefuse.user_profile.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.codefuse.core_domain.repository.UserProfileRepository
import com.app.codefuse.user_profile.model.ProfileListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileListViewModel @Inject constructor(
    private val repository: UserProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileListUiState>(ProfileListUiState.Loading)
    val uiState: StateFlow<ProfileListUiState> = _uiState.asStateFlow()

    init {
        loadProfiles()
    }

    fun loadProfiles() {
        viewModelScope.launch {
            _uiState.value = ProfileListUiState.Loading
            try {
                val users = repository.fetchProfiles(count = 50)
                _uiState.value = ProfileListUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = ProfileListUiState.Error(e.localizedMessage ?: "Unknown Error")
                e.printStackTrace()
            }
        }
    }
}