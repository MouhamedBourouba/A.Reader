package com.example.areader.prestion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.areader.prestion.screens.HomeScreen
import com.example.areader.prestion.screens.SearchScreen.SearchScreen
import com.example.areader.prestion.screens.detailsScreen.BookDetailsScreen
import com.example.areader.prestion.screens.loginScreen.AuthScreen
import com.example.areader.prestion.screens.splash.SplashScreen
import com.example.areader.utils.Constants.DETAIL_ARG_KEY
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
        composable(route = Screens.Search.route) {
            SearchScreen(navHost)
        }
        composable(
            route = Screens.BookDetails.route + "/{$DETAIL_ARG_KEY}",
            arguments = listOf(
                navArgument(DETAIL_ARG_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val bookUrl = it.arguments!!.getString(DETAIL_ARG_KEY)
            BookDetailsScreen(navController = navHost, bookUrl)
        }
    }

}

