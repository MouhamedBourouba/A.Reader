package com.example.areader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.areader.prestion.screens.HomeScreen
import com.example.areader.prestion.screens.loginScreen.AuthScreen
import com.example.areader.prestion.screens.splash.SplashScreen
import com.example.areader.utils.Screens


@Composable
fun Navigation() {
    val navHost = rememberNavController()

    NavHost(
        navController = navHost,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Login.route) {
            AuthScreen(navController = navHost)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navHost)
        }
        composable(route = Screens.Splash.route) {
            SplashScreen(navHost)
        }
    }

}

