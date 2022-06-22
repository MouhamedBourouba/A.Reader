package com.example.areader.prestion.screens.HomeSceeen

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.request.auth.TokenRequest
import com.example.areader.data.Dto.AuthDto.UserResponse
import com.example.areader.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefs: SharedPreferences
) : ViewModel() {

    val currentUser = mutableStateOf(UserResponse())
    private val _singOuChannel = Channel<Unit>()
    val singOuChannel = _singOuChannel.receiveAsFlow()
    val userName = currentUser.value.userName

    init {
        getCurrentUser()
    }


    private fun singOut() {
        viewModelScope.launch {
            try {
                prefs.edit()
                    .remove("jwt")
                    .apply()
                _singOuChannel.send(Unit)
            } catch (e: Exception) {
                // TODO: Throw Exception
            }
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            val user =
                authRepository.getUser(TokenRequest(prefs.getString("jwt", null) ?: return@launch))
            currentUser.value = (user.data ?: onEvent(HomeScreenUiEvent.SingOut)) as UserResponse
            Log.d("singUp", "getCurrentUser: ${user.data}")
        }
    }

    fun onEvent(homeScreenUiEvent: HomeScreenUiEvent) {
        when (homeScreenUiEvent) {
            is HomeScreenUiEvent.SingOut -> singOut()
        }
    }


}