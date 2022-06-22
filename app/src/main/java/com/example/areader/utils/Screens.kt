package com.example.areader.utils

sealed class Screens(val route: String) {
    object Login : Screens("login_screen")
    object Home : Screens("home_screen")
    object Splash: Screens("splash_screen")
}