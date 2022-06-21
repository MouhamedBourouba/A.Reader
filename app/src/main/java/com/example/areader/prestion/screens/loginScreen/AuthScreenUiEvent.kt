package com.example.areader.prestion.screens.loginScreen

sealed class AuthScreenUiEvent {
    data class RegisterEmailTextChanged(val value: String) : AuthScreenUiEvent()
    data class RegisterUsernameTextChanged(val value: String) : AuthScreenUiEvent()
    data class RegisterPasswordTextChanged(val value: String) : AuthScreenUiEvent()

    object SingUp: AuthScreenUiEvent()


    data class LoginUsernameOrEmailTextChanged(val value: String) : AuthScreenUiEvent()
    data class LoginPasswordTextChanged(val value: String) : AuthScreenUiEvent()

    object SingIn: AuthScreenUiEvent()
}
