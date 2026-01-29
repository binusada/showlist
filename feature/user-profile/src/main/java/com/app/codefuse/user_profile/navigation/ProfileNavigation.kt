package com.app.codefuse.user_profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.codefuse.user_profile.ui.list.ProfileListScreen

fun NavGraphBuilder.profileScreen(onUserClick: (String) -> Unit) {
    composable("profile_list") {
        ProfileListScreen()
        // You would pass onUserClick down to the ProfileCard here
    }
}