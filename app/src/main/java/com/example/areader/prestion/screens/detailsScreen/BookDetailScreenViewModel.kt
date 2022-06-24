package com.example.areader.prestion.screens.detailsScreen

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.Dto.GoogleBooksDto.Item
import com.example.areader.data.Resource
import com.example.areader.model.MBook
import com.example.areader.repository.details.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailScreenViewModel @Inject constructor(
    private val preferences: SharedPreferences,
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    var currentBook: MutableState<Item?> = mutableStateOf(null)
    var mBook = MBook()
    val loadingBookData = mutableStateOf(true)
    val loadingSavingBook  = mutableStateOf<Boolean?>(null)
    private val _exception = Channel<Unit>()
    val exception = _exception.receiveAsFlow()


    fun getBookData(bookId: String) = viewModelScope.launch {
        loadingBookData.value = true
        when (val book = detailsRepository.getCurrentBookData(bookId)) {
            is Resource.Failed -> _exception.send(Unit)
            is Resource.Success -> currentBook.value = book.data
        }
        loadingBookData.value = false
    }

    fun saveBook() = viewModelScope.launch {
        loadingSavingBook.value = true


            mBook = MBook(
                title = currentBook.value?.volumeInfo?.title,
                authors = currentBook.value?.volumeInfo?.authors.toString(),
                isReading = false,
                startReading = System.currentTimeMillis(),
                rate = 0.0,
                description = currentBook.value?.volumeInfo?.description,
                imageUrl = currentBook.value?.volumeInfo?.imageLinks?.thumbnail ?: currentBook.value?.volumeInfo?.imageLinks?.smallThumbnail,
                finishedReading = null,
                googleBookApiId = currentBook.value?.id
            )



        detailsRepository.addBookToUser(preferences.getString("userName", null) ?: return@launch, mBook)


        loadingSavingBook.value = false
    }

}