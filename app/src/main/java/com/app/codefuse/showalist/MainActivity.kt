package com.app.codefuse.showalist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.app.codefuse.showalist.ui.theme.ShowAListTheme
import com.app.codefuse.user_profile.navigation.PROFILE_LIST_ROUTE
import com.app.codefuse.user_profile.navigation.profileGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowAListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = PROFILE_LIST_ROUTE) {
                        profileGraph(navController)
                    }
                }
            }
        }
    }
}