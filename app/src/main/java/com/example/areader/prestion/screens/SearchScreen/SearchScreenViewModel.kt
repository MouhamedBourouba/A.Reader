package com.example.areader.prestion.screens.SearchScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.Dto.GoogleBooksDto.BookDto
import com.example.areader.data.Resource
import com.example.areader.data.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    var loading by mutableStateOf(false)
    var searchText by mutableStateOf("")
    var bookData by mutableStateOf<BookDto?>(null)
    private val _ExceptionChannel = Channel<String>()
    val exceptionChannel = _ExceptionChannel.receiveAsFlow()

    init {
        searchInBooksApi("search", true)
    }


    fun searchInBooksApi(query: String, fromInit: Boolean = false) = viewModelScope.launch() {
        loading = true
        val searchText = searchText.ifEmpty { "search" }
        delay(1000)
        // check if user stop typing after 1s
        if (fromInit || query == searchText) {
            when (val books = repository.preformSearch(searchText)) {
                is Resource.Success -> {
                    loading = false
                    bookData = books.successData
                }
                is Resource.Failed -> {
                    _ExceptionChannel.send(books.message.toString())
                    loading = false
                }
            }
        }
    }

    fun onEvent(searchUiEvent: SearchUiEvent) {
        if (searchUiEvent is SearchUiEvent.SearchTextChanged) {
            searchText = searchUiEvent.text
            viewModelScope.launch {
                searchInBooksApi(searchUiEvent.text)
            }
        }
    }


}