package com.example.areader.prestion.screens.loginScreen

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.auth.AuthResult
import com.example.areader.data.auth.request.SingInAuthRequest
import com.example.areader.data.auth.request.SingUpAuthRequest
import com.example.areader.data.auth.response.SingInResponse
import com.example.areader.data.auth.response.SingUpResponse
import com.example.areader.repository.Repository
import com.example.areader.utils.Constants.TAG
import com.example.areader.utils.TextUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository,
    private val textUtils: TextUtils,
    private val prefs: SharedPreferences
    ) : ViewModel() {

    var loginState by mutableStateOf(LoginScreenState())
    private val singInChannel = Channel<AuthResult<SingInResponse>>()
    private val singUpChannel = Channel<AuthResult<SingUpResponse>>()
    val singInResult = singInChannel.receiveAsFlow()
    val singUpResult = singUpChannel.receiveAsFlow()
    var isAuthenticate by mutableStateOf(false)

    init {
        authenticate()
    }


    fun onEvent(authEvent: AuthScreenUiEvent) {
        when (authEvent) {
            is AuthScreenUiEvent.LoginUsernameOrEmailTextChanged -> {
                loginState = loginState.copy(loginUserNameOrEmailText = authEvent.value)
                enableSingInButton()
            }
            is AuthScreenUiEvent.LoginPasswordTextChanged -> {
                loginState = loginState.copy(loginPasswordText = authEvent.value)
                enableSingInButton()
            }
            is AuthScreenUiEvent.RegisterEmailTextChanged -> {
                loginState = loginState.copy(registerEmailText = authEvent.value)
                enableSingUpButton()
            }
            is AuthScreenUiEvent.RegisterUsernameTextChanged -> {
                loginState = loginState.copy(registerUserNameText = authEvent.value)
                enableSingUpButton()
            }
            is AuthScreenUiEvent.RegisterPasswordTextChanged -> {
                loginState = loginState.copy(registerPasswordText = authEvent.value)
                enableSingUpButton()
            }

            is AuthScreenUiEvent.SingIn -> loginWithEmailAndPassword(
                SingInAuthRequest(
                    loginState.loginUserNameOrEmailText,
                    loginState.loginPasswordText
                )
            )

            is AuthScreenUiEvent.SingUp -> createUser(
                SingUpAuthRequest(
                    loginState.registerUserNameText,
                    loginState.registerEmailText,
                    loginState.registerPasswordText
                )
            )
        }
    }

    private fun enableSingInButton() {
        loginState.isSingInButtonEnabled = (
                loginState.loginUserNameOrEmailText.isNotEmpty() && loginState.loginPasswordText.isNotEmpty())

    }

    private fun enableSingUpButton() {
        loginState.isSingUpButtonEnabled = (
                loginState.registerEmailText.isNotEmpty()
                        && loginState.registerPasswordText.isNotEmpty()
                        && loginState.registerUserNameText.isNotEmpty())
    }

    private fun loginWithEmailAndPassword(
        singInAuthRequest: SingInAuthRequest
    ) = viewModelScope.launch {
        Log.d(TAG, "loginWithEmailAndPassword: trying to login")
        loginState = loginState.copy(isLoading = true)

        val isSuccess = repository.singIn(singInAuthRequest)

        singInChannel.send(isSuccess)

        loginState = loginState.copy(isLoading = false)

    }



    private fun createUser(
        user: SingUpAuthRequest
    ) = viewModelScope.launch {
        loginState = loginState.copy(isLoading = true)
        if (textUtils.isValidEmail(user.email)) {

            val authResult = repository.singUp(user)
            singUpChannel.send(authResult)

        } else {
            singUpChannel.send(AuthResult.UnAuthorized("Email is badly formatted"))
        }

        loginState = loginState.copy(isLoading = false)
    }

    private fun authenticate() {
        viewModelScope.launch {
            isAuthenticate = repository.isAuthenticate()
        }
    }
}
