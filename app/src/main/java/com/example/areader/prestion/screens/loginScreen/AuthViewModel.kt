package com.example.areader.prestion.screens.loginScreen

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
import com.example.areader.prestion.screens.loginScreen.LoginScreenState
import com.example.areader.prestion.screens.loginScreen.AuthScreenUiEvent
import com.example.areader.repository.Repository
import com.example.areader.repository.RepositoryImp
import com.example.areader.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repositoryImp: Repository) : ViewModel() {

    var loginState by mutableStateOf(LoginScreenState())
    private val singInChannel = Channel<AuthResult<SingInResponse>>()
    private val singUpChannel = Channel<AuthResult<SingUpResponse>>()
    val singInResult = singInChannel.receiveAsFlow()
    val singUpResult = singUpChannel.receiveAsFlow()

    fun onEvent(authEvent: AuthScreenUiEvent) {
        when (authEvent) {

            is AuthScreenUiEvent.LoginUsernameOrEmailTextChanged -> loginState = loginState.copy(loginUserNameOrEmailText = authEvent.value)
            is AuthScreenUiEvent.LoginPasswordTextChanged -> loginState = loginState.copy(loginPasswordText = authEvent.value)
            is AuthScreenUiEvent.RegisterEmailTextChanged -> loginState = loginState.copy(registerEmailText = authEvent.value)
            is AuthScreenUiEvent.RegisterUsernameTextChanged -> loginState = loginState.copy(registerUserNameText = authEvent.value)
            is AuthScreenUiEvent.RegisterPasswordTextChanged -> loginState = loginState.copy(registerPasswordText = authEvent.value)

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


    init {
        viewModelScope.launch {
           repositoryImp.isAuthenticate()
        }
    }

    private fun loginWithEmailAndPassword(
       singInAuthRequest: SingInAuthRequest
    ) = viewModelScope.launch {
        Log.d(TAG, "loginWithEmailAndPassword: trying to login")
        loginState = loginState.copy(isLoading = true)

        val isSuccess = repositoryImp.singIn(singInAuthRequest)

        singInChannel.send(isSuccess)

        loginState = loginState.copy(isLoading = false)

    }


    private fun createUser(
        user: SingUpAuthRequest
    ) = viewModelScope.launch {
        loginState = loginState.copy(isLoading = true)

        val authResult = repositoryImp.singUp(user)
        singUpChannel.send(authResult)


        loginState = loginState.copy(isLoading = false)
    }
}