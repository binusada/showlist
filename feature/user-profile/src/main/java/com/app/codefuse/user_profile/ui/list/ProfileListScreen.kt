package com.app.codefuse.user_profile.ui.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.codefuse.core_domain.models.User
import com.app.codefuse.core_ui.user.ProfileCard
import com.app.codefuse.user_profile.model.ProfileListUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileListScreen(
    viewModel: ProfileListViewModel = hiltViewModel(),
    onProfileClick: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("User Profiles") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val s = state) {
                is ProfileListUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                is ProfileListUiState.Success -> ProfileList(s.profiles, onProfileClick)
                is ProfileListUiState.Error -> ErrorView(s.message) { viewModel.loadProfiles() }
            }
        }
    }
}

@Composable
fun ProfileList(
    profiles: List<User>,
    onProfileClick: (String) -> Unit
    ) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(profiles) { user ->
            ProfileCard(
                fullName = user.fullName,
                email = user.email,
                location = user.location,
                imageUrl = user.imageUrl,
                onClick = {
                    Log.d("passing --->-", user.id)
                    onProfileClick(user.id)
                }
            )
        }
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}
