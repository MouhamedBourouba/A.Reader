package com.example.areader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.areader.prestion.screens.HomeScreen
import com.example.areader.prestion.screens.LoginScreen
import com.example.areader.utils.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation() {
    val navHost = rememberNavController()
    NavHost(
        navController = navHost,
        startDestination = if (FirebaseAuth.getInstance().currentUser == null) Screens.Login.route else Screens.Home.route
    ) {
        composable(route = Screens.Login.route) {
            LoginScreen(navController = navHost)
        }
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
    }

}

