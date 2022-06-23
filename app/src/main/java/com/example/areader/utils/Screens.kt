package com.example.areader.utils

sealed class Screens(val route: String) {
    object Login : Screens("login_screen")
    object Home : Screens("home_screen")
    object Splash: Screens("splash_screen")
    object Search: Screens("search_screen")
    object BookDetails: Screens("details_screen")
}