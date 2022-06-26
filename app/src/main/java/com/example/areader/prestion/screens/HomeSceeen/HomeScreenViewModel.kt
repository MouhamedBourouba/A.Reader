package com.example.areader.prestion.screens.HomeSceeen

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.Dto.AuthDto.UserRespond
import com.example.areader.data.Resource
import com.example.areader.model.MBook
import com.example.areader.data.repository.home.HomeRepository
import com.example.areader.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val prefs: SharedPreferences
) : ViewModel() {

    val currentUser = mutableStateOf(UserRespond())
    val loadingBooks = mutableStateOf(false)
    private val _singOuChannel = Channel<Unit>()
    val singOuChannel = _singOuChannel.receiveAsFlow()
    val userName = prefs.getString("userName", null)
    val readingList = mutableListOf<MBook>()
    val pendingList = mutableListOf<MBook>()
    val completedList = mutableListOf<MBook>()

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
//        loading.value = true
        viewModelScope.launch {
            loadingBooks.value = true
            val user =
                homeRepository.getUserDataByName(prefs.getString("userName", null) ?: return@launch)
            loadingBooks.value = false
            when (user) {
                is Resource.Success -> {
                    currentUser.value =
                        ((user.data ?: onEvent(HomeScreenUiEvent.SingOut)) as UserRespond)
                    Log.d(TAG, "getCurrentUser: $user")

                    currentUser.value.userBooks?.forEach { mBook ->
                        when (mBook.isReading) {
                            true -> {
                                readingList.add(mBook)
                            }
                            false -> {
                                pendingList.add(mBook)
                            }
                            else -> {
                                completedList.add(mBook)
                            }
                        }
//                        loading.value = true
                    }
                }
                is Resource.Failed -> {
                    Log.d(TAG, "getCurrentUser: $user")
                    onEvent(HomeScreenUiEvent.SingOut)
                }

            }


        }
//        loading.value = false
//        Log.d(TAG, "getCurrentUser: $loading")
    }

    fun onEvent(homeScreenUiEvent: HomeScreenUiEvent) {
        when (homeScreenUiEvent) {
            is HomeScreenUiEvent.SingOut -> singOut()
        }
    }


}