package com.example.areader.prestion.screens.HomeSceeen

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.auth.request.TokenRequest
import com.example.areader.data.auth.response.UserResponse
import com.example.areader.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val prefs: SharedPreferences
    ) : ViewModel() {

    val currentUser = mutableStateOf(UserResponse())


    init {
        getCurrentUser()
    }


    private fun singOut() {

    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            repository.getUser(TokenRequest(prefs.getString("jwt", null) ?: return@launch))
        }
    }

    fun onEvent(homeScreenUiEvent: HomeScreenUiEvent) {
        when(homeScreenUiEvent) {
            is HomeScreenUiEvent.SingOut -> {
                prefs.edit()
                    .remove("jwt")
                    .apply()
            }
        }
    }



}