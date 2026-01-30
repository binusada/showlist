package com.app.codefuse.user_profile.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.codefuse.user_profile.ui.details.ProfileDetailScreen
import com.app.codefuse.user_profile.ui.list.ProfileListScreen

const val USER_ID_ARG = "userId"
const val PROFILE_LIST_ROUTE = "profile_list_route"
const val PROFILE_DETAIL_ROUTE = "profile_detail_route"

fun NavController.navigateToProfileList(navOptions: NavOptions? = null) {
    this.navigate(PROFILE_LIST_ROUTE, navOptions)
}

fun NavController.navigateToProfileDetail(userId: String) {
    this.navigate("$PROFILE_DETAIL_ROUTE/$userId")
}

fun NavGraphBuilder.profileGraph( navController: NavController) {
    composable(route = PROFILE_LIST_ROUTE) {
        ProfileListScreen(
            onProfileClick = { userId ->
                Log.d("Inside Nav ---->", userId)
                navController.navigateToProfileDetail(userId)
            }
        )
    }
    composable(
        route = "$PROFILE_DETAIL_ROUTE/{$USER_ID_ARG}",
        arguments = listOf(navArgument(USER_ID_ARG) { type = NavType.StringType })
    ) { backStackEntry ->
        ProfileDetailScreen() }
}


