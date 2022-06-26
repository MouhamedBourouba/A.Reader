package com.example.areader.prestion.screens.updateScreen

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.model.MBook
import com.example.areader.data.repository.updateBooks.UpdateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateBookScreenViewModel @Inject constructor(
    val prefs: SharedPreferences,
    private val updateRepository: UpdateRepository
) : ViewModel() {


    val loading  = mutableStateOf<Boolean?>(null)

    fun updateBook(mBook: MBook) {
        viewModelScope.launch {
            loading.value = true
            updateRepository.updateBook(
                prefs.getString("userName", "").toString(),
                mBook.googleBookApiId.toString(),
                mBook
            )
            loading.value = false
        }
    }


}