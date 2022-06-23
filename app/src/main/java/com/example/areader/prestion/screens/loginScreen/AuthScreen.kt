package com.example.areader.prestion.screens.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.areader.prestion.components.ShowLoading
import com.example.areader.prestion.components.ShowRegister
import com.example.areader.prestion.components.login.ShowLogin
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AuthScreen(navController: DestinationsNavigator, viewModel: AuthViewModel = hiltViewModel()) {

    val isLogin = rememberSaveable {
        mutableStateOf(true)
    }

    if (isLogin.value) ShowLogin(
        viewModel,
        navController,
    ) { isLogin.value = false }
    else ShowRegister(
        viewModel,
        navController
    ) { isLogin.value = true }

    if (viewModel.loginState.isLoading) ShowLoading()

}

