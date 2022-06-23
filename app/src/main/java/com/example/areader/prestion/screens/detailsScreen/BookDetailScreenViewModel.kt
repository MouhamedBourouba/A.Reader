package com.example.areader.prestion.screens.detailsScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.data.Resource
import com.example.areader.repository.details.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailScreenViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    var currentBook: MutableState<Item?> = mutableStateOf(null)
    val loading = mutableStateOf(true)
    private val _exception = Channel<Unit>()
    val exception = _exception.receiveAsFlow()


    fun getBookData(bookId: String) = viewModelScope.launch {
        loading.value = true
        when (val book = detailsRepository.getCurrentBookData(bookId)) {
            is Resource.Failed -> _exception.send(Unit)
            is Resource.Success -> currentBook.value = book.data
        }
        loading.value = false
    }

}