package com.example.areader.prestion.screens.detailsScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BookDetailsScreen(
    navController: NavController,
    bookUrl: String?
){
    Log.d("BookDetailsScreen", "BookDetailsScreen: $bookUrl")
}