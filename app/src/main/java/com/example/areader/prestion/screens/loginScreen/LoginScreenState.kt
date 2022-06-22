package com.example.areader.prestion.screens.loginScreen

data class LoginScreenState(
    var isLoading: Boolean = false,
    var loginUserNameOrEmailText: String = "",
    var loginPasswordText: String = "",
    val isLoginScreen: Boolean = true,
    var registerUserNameText: String = "",
    var registerEmailText: String = "",
    var registerPasswordText: String = "",
    var isSingInButtonEnabled: Boolean = false,
    var isSingUpButtonEnabled: Boolean = false,
)
