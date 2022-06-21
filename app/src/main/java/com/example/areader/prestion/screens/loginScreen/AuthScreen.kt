package com.example.areader.prestion.screens.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.areader.prestion.components.ShowLoading
import com.example.areader.prestion.components.ShowRegister
import com.example.areader.prestion.components.login.ShowLogin

@Composable
fun AuthScreen(viewModel: AuthViewModel = hiltViewModel(), navController: NavController) {

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

